package io.github.mongsil3344.ikeeper.bementoring.userserver.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    public record Hello(String message) {

    }

    @GetMapping("/hello")
    public Hello hello() {
        return new Hello("Hello World!");
    }
}
