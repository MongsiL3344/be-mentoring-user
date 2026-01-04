package io.github.mongsil3344.ikeeper.bementoring.user;

import io.github.mongsil3344.ikeeper.bementoring.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final이 붙은 필드를 생성자 주입
public class UserService {

    private final UserRepository userRepository;

    /**
     * 이메일로 유저를 찾는 메서드
     * @param email 찾길 원하는 회원의 email
     * @return UserResponse
     */
    public UserResponse findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));

        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getName()
        );
    }
}
