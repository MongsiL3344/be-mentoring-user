package io.github.mongsil3344.ikeeper.bementoring.userserver.user;

import io.github.mongsil3344.ikeeper.bementoring.userserver.user.dto.CreateUserRequest;
import io.github.mongsil3344.ikeeper.bementoring.userserver.user.dto.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 유저 찾기 GET 요청 진입점
     * 쿼리파라미터에 email이 String으로 담겨서 와야됨
     * @param email 찾을 유저의 이메일
     * @return UserResponse (DTO)
     */
    @GetMapping("/users")
    public ResponseEntity<UserResponse> getUser(@RequestParam @NotBlank @Email String email) {
        log.info("find user request received, email: {}", email);

        UserResponse res = userService.findUserByEmail(email);

        return ResponseEntity.ok(res);
    }

    /**
     * 유저 생성 POST 요청 진입점
     * POST 요청 바디에 CreateUserRequest 필드에 맞는 값을 담아서 보내야함
     * @param req CreateUserRequest (DTO)
     * @return UserResponse (DTO)
     */
    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest req) {
        log.info("user create request received: {}", req.email());

        UserResponse res = userService.createUser(req);

        log.info("user create success: {}", res.email());
        return ResponseEntity.created(null).body(res);
    }
}
