package com.timoxino.tryout.reactive.spring.rsocket.controller;

import com.timoxino.tryout.reactive.spring.rsocket.DeviceProducer;
import com.timoxino.tryout.reactive.spring.rsocket.model.InternetDevice;
import com.timoxino.tryout.reactive.spring.rsocket.model.ReactiveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
@RequiredArgsConstructor
public class RSocketDeviceController {

    private final DeviceProducer producer;

    @MessageMapping("devices")
    Publisher<InternetDevice> listDevices(ReactiveRequest request) {
        String macAddress = request.getMacAddress();
        log.info("RSocket connectivity established and request came in with mac address = {}", macAddress);
        return producer.produce(macAddress);
    }
}
