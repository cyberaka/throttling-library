package com.cyberaka.practice.throttling.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * My awesome REST endpoint.
 */
@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return "Hello!";
    }

}
