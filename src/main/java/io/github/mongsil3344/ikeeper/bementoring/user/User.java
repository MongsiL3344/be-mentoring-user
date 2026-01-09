package io.github.mongsil3344.ikeeper.bementoring.user;

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

    @Id
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
     * id 필드는 DB가 생성해줘야하기때문에 AllArgsConstructor 어노테이션을 사용하지 않고 직접 생성자를 만듦
     */
    private User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * 이름, 이메일, 비밀번호를 받아서 새로운 User 엔티티의 객체를 생성
     * @param name 유저명
     * @param email 이메일
     * @param password 해싱된 비밀번호
     */
    public static User of(String name, String email, String password) {
        return new User(name, email, password);
    }
}
