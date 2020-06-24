package com.timoxino.tryout.reactive.spring.rsocket;

import com.timoxino.tryout.reactive.spring.rsocket.model.InternetDevice;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DeviceProducer {
  public Flux<InternetDevice> produce(String macAddress) {
    return Flux.fromStream(Stream.generate(() -> new InternetDevice(macAddress + "@" + Instant.now()))).delayElements(Duration.ofSeconds(2));
  }
}
