package io.github.mongsil3344.ikeeper.bementoring.userserver.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 유저 생성 요청 DTO
 * 해당 DTO를 받는 컨트롤러에서 @Valid 어노테이션을 사용해야 검증이 진행됨
 */
public record CreateUserRequest(

    @NotBlank(message = "이름은 필수값입니다")
    String name,

    @NotBlank(message = "이메일은 필수값입니다")
    @Email(message = "이메일 형식이 잘못되었습니다")
    String email,

    @NotBlank(message = "비밀번호는 필수값입니다")
    String password
) {

}
