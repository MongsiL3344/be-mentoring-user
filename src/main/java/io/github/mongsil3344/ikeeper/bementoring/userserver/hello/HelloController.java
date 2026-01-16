package io.github.mongsil3344.ikeeper.bementoring.userserver.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    public record Hello(String message) {

    }

    @GetMapping("/hello")
    public ResponseEntity<Hello> hello() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new Hello("Hello World!"));
    }
}
