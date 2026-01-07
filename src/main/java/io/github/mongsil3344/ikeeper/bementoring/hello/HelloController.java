package io.github.mongsil3344.ikeeper.bementoring.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    /**
     * message를 객체로 리턴하여 json으로 보여주기 위한 DTO
     * record는 컴팩트생성자로 생성자를 명시하지 않아도 객체생성시 묵시적으로 값을 넣어줌
     */
    public record Hello(String message) {

    }

    @GetMapping("/hello")
    public Hello hello() {
        // Hello DTO의 생성자로 객체를 만든 다음 그걸 바로 리턴
        return new Hello("Hello World!");
    }
}
