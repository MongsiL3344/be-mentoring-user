package io.github.mongsil3344.ikeeper.bementoring.user;

import io.github.mongsil3344.ikeeper.bementoring.user.dto.CreateUserRequest;
import io.github.mongsil3344.ikeeper.bementoring.user.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor // final이 붙은 필드를 생성자 주입
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 이메일로 유저를 찾는 메서드
     * @param email 컨트롤러에서 받은 email
     * @return UserResponse (DTO)
     */
    @Transactional(readOnly = true)
    public UserResponse findUserByEmail(String email) {
        //레포지토리에서 이메일로 유저를 찾고 해당 객체를 user로 생성
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> {
                //만약 찾지 못했을경우 오류발생
                log.error("해당 이메일의 유저가 없습니다 : {}", email);
                return new IllegalArgumentException("User not found: " + email);
            });

        // 찾은 user를 DTO객체에 담아서 반환
        return UserResponse.from(user);
    }

    /**
     * 유저 생성 메서드
     * @param req 컨트롤러에서 받은 CreateUserRequest (DTO)
     * @return UserResponse (DTO)
     */
    @Transactional
    public UserResponse createUser(CreateUserRequest req) {

        // 만약 해당 이메일의 유저가 이미 존재하면 에러 반환
        // findByEmail을 옵셔널로 선언해뒀기 때문에 isPresent로 null이 아닌지 확인할수있음
        if (userRepository.findByEmail(req.email()).isPresent()) {
            log.error("이미 존재하는 이메일입니다: {}", req.email());
            throw new IllegalArgumentException("User already exists: " + req.email());
        }

        // 해싱된 비밀번호 생성
        String hashedPw = passwordEncoder.encode(req.password());

        // 이름, 이메일, 해싱된 비밀번호로 유저 엔티티 객체 생성
        User user = User.of(req.name(), req.email(), hashedPw);

        // 위에서 만든 유저객체를 JPA 메서드 save로 DB에 저장
        userRepository.save(user);

        // 생성된 유저 정보를 응답용 DTO 객체에 담아서 반환
        return UserResponse.from(user);
    }
}
