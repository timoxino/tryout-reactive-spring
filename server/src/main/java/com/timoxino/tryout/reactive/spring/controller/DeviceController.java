package com.timoxino.tryout.reactive.spring.controller;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import com.timoxino.tryout.reactive.spring.DeviceProducer;
import com.timoxino.tryout.reactive.spring.model.InternetDevice;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Log4j2
@AllArgsConstructor
public class DeviceController {

  private DeviceProducer deviceProducer;

  @GetMapping(value = "/hardcoded-devices")
  public Flux<InternetDevice> listDevices() {

    Flux<InternetDevice> deviceFlux = Flux.just("MAC1", "MAC2", "MAC3", "MAC_N").map(InternetDevice::new);
    deviceFlux.subscribe(log::warn);
    return deviceFlux;
  }

  @GetMapping(value = "/generated-devices/{mac}", produces = TEXT_EVENT_STREAM_VALUE)
  public Flux<InternetDevice> listGeneratedDevices(@PathVariable String mac) {
    return deviceProducer.produce(mac);
  }
}
