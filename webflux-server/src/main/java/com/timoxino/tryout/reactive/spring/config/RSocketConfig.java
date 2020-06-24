package com.timoxino.tryout.reactive.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;

@Configuration
public class RSocketConfig {
  @Bean
  RSocketRequester rSocketRequester(RSocketStrategies rSocketStrategies) {
    return RSocketRequester.builder()
            .rsocketStrategies(rSocketStrategies)
            .connectTcp("localhost", 7070)
            .block();
  }
}
