package io.github.mongsil3344.ikeeper.bementoring.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA가 레포지토리를 사용할 수 있게 interface로 선언하고 JpaRepository를 상속
 */
public interface UserRepository extends JpaRepository<User, Long> {

    //JPA 쿼리메서드 (이메일로 유저 찾기)
    // 옵셔널로 선언해서 쿼리의 결과가 null일 수도 있음을 명시 + 상위레이어에서 에러처리할 수 있게끔
    Optional<User> findByEmail(String email);
}
