package com.timoxino.tryout.reactive.spring.controller;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import com.timoxino.tryout.reactive.spring.rsocket.model.InternetDevice;
import com.timoxino.tryout.reactive.spring.rsocket.model.ReactiveRequest;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@Log4j2
public class DeviceController {

  private final RSocketRequester rSocketRequester;

  @GetMapping(value = "/webflux/devices", produces = TEXT_EVENT_STREAM_VALUE)
  public Flux<InternetDevice> listDevices() {
    Flux<InternetDevice> deviceFlux =
            Flux.just("MAC1", "MAC2", "MAC3", "MAC_N").map(InternetDevice::new).delayElements(Duration.ofSeconds(2));
    deviceFlux.subscribe(log::warn);
    return deviceFlux;
  }

  @GetMapping(value = "/rsocket/devices/{mac}", produces = TEXT_EVENT_STREAM_VALUE)
  public Publisher<InternetDevice> listStreamedDevices(@PathVariable String mac) {
    return rSocketRequester.route("devices").data(new ReactiveRequest(mac)).retrieveFlux(InternetDevice.class);
  }

  @GetMapping(value = "/rsocket/devices/failing/{mac}", produces = TEXT_EVENT_STREAM_VALUE)
  public Publisher<InternetDevice> listFailingDevices(@PathVariable String mac) {
    return rSocketRequester.route("failing-devices").data(new ReactiveRequest(mac)).retrieveFlux(InternetDevice.class);
  }

  @GetMapping(value = "/rsocket/registrations")
  public Publisher<Void> register() {
    return rSocketRequester.route("registrations").data(new ReactiveRequest(String.valueOf(Math.random()))).send();
  }
}
