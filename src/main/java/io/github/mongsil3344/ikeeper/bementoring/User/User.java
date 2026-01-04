package io.github.mongsil3344.ikeeper.bementoring.User;

import jakarta.persistence.*;
import lombok.*;

/**
 * 회원관리를 위한 users 테이블에 매칭되는 User 엔티티
 */
@Entity // JPA가 해당 클래스를 관리하도록 지정
@Table(name = "users") // Entity와 매핑할 테이블 지정
@NoArgsConstructor // 인자가 없는 생성자를 자동으로 생성 (JPA를 사용하려면 인자가 없는 생성자가 필요함)
@Getter // 필드에 맞는 Getter를 자동으로 붙여줌
@Setter // 필드에 맞는 Setter를 자동으로 붙여줌
public class User {

    @Id // PK 지정
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;
}
