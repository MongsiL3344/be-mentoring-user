package io.github.mongsil3344.ikeeper.bementoring.user;

import io.github.mongsil3344.ikeeper.bementoring.user.dto.CreateUserRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Getter;

/**
 * 회원관리를 위한 users 테이블에 매칭되는 User 엔티티
 */
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 300)
    private String password;

    /**
     * User 객체 생성자
     * private로 외부에 노출하지 않고 팩토리 메서드로 내부에서 객체 생성
     */
    private User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * User 엔티티의 정적 팩토리 메서드
     * @param name, email, password
     */
    public static User of(String name, String email, String password) {
        return new User(name, email, password);
    }
}
