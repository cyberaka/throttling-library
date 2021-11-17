package com.cyberaka.practice.throttling;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Hello!");
    }

    @Test
    public void generate5secondLoad() throws Exception {
        Instant timestamp = Instant.now();
        Duration res;
        do {
            assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Hello!");
            res = Duration.between(timestamp, Instant.now());
        } while (res.getSeconds() < 5);
    }
}
