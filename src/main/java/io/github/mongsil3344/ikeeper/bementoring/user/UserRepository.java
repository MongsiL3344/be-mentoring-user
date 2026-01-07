package io.github.mongsil3344.ikeeper.bementoring.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA가 레포지토리를 사용할 수 있게 interface로 선언하고 JpaRepository를 상속
 */
public interface UserRepository extends JpaRepository<User, Long> {

    //JPA 쿼리메서드 (이메일로 유저 찾기)
    Optional<User> findByEmail(String email);
}
