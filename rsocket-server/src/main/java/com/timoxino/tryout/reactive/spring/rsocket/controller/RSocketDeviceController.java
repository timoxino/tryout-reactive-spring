package com.timoxino.tryout.reactive.spring.rsocket.controller;

import com.timoxino.tryout.reactive.spring.rsocket.DeviceProducer;
import com.timoxino.tryout.reactive.spring.rsocket.model.InternetDevice;
import com.timoxino.tryout.reactive.spring.rsocket.model.ReactiveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Log4j2
@RequiredArgsConstructor
public class RSocketDeviceController {

    private final DeviceProducer producer;

    @MessageMapping("devices")
    Publisher<InternetDevice> listDevices(ReactiveRequest request) {
        String macAddress = request.getMacAddress();
        log.info("RSocket connectivity established and request to list came in with mac address = {}", macAddress);
        return producer.produce(macAddress);
    }

    @MessageMapping("registrations")
    Mono<Void> register(ReactiveRequest request) {
        log.info("RSocket connectivity established and request to register came in with mac address = {}",
                request.getMacAddress());
        return Mono.empty();
    }
}
