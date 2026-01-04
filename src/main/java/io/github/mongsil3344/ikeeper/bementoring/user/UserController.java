package io.github.mongsil3344.ikeeper.bementoring.user;

import io.github.mongsil3344.ikeeper.bementoring.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/finduser")
    public UserResponse getUser(@RequestParam String email) {
        System.out.println("user email received : " + email);
        return userService.findUserByEmail(email);
    }
}
