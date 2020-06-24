package com.timoxino.tryout.reactive.spring.rsocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactiveRequest {
  private String macAddress;
}
