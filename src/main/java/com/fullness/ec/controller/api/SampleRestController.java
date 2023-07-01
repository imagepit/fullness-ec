package com.fullness.ec.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {
  @GetMapping("/api/sample")
  public String sample() {
    return "Hello World!";
  }
}
