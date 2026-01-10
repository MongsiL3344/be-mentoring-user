package io.github.mongsil3344.ikeeper.bementoring.userserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 비밀번호 해싱을 위한 클래스
 * 비크립트 패키지의 passwordEncoder를 활용하여 비밀번호를 해싱
 * 사용은 UserService에서 하고 스프링 Bean으로 등록해 자동 DI 되도록 구성
 * 라운드 수는 무난하게 12로 설정함
 */
@Configuration
public class PasswordConfig {

    // 이 부분이 스프링 Bean으로 등록되어서 UserService로 DI
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
