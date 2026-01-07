package io.github.mongsil3344.ikeeper.bementoring.user;

import io.github.mongsil3344.ikeeper.bementoring.user.dto.CreateUserRequest;
import io.github.mongsil3344.ikeeper.bementoring.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/finduser")
    public UserResponse getUser(@RequestParam String email) {
        log.info("find user request received, email: {}", email);

        return userService.findUserByEmail(email);
    }

    @PostMapping("/createuser")
    public UserResponse createUser(@RequestBody @Valid CreateUserRequest req) {
        log.info("user create request received: {}", req.email());

        UserResponse res = userService.createUser(req);

        log.info("user create success: {}", res.email());
        return res;
    }
}
