package io.github.mongsil3344.ikeeper.bementoring.userserver.hello;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    public record Hello(String message) {

    }

    @GetMapping("/hello")
    public ResponseEntity<Hello> hello(@RequestHeader(name = "X-Mentoring", required = true) String headerValue) {
        log.info("hello controller called");
        log.info("Header: {}", headerValue);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new Hello("Hello World!"));
    }
}
