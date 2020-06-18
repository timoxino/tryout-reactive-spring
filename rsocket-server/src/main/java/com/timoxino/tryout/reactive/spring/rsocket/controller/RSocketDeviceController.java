package com.timoxino.tryout.reactive.spring.rsocket.controller;

import java.time.Duration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class RSocketDeviceController {

    @MessageMapping("devices")
    Flux<String> listDevices() {
        return Flux.just("test1", "test2", "test3").delayElements(Duration.ofSeconds(1));
    }
}
