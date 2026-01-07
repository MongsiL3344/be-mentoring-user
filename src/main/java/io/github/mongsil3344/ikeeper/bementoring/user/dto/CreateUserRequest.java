package io.github.mongsil3344.ikeeper.bementoring.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 유저 생성 요청 DTO (Record)
 */
public record CreateUserRequest(
    @NotBlank(message = "이름은 필수값입니다") 
    String name,
    @NotBlank(message = "이메일은 필수값입니다") 
    @Email(message = "이메일 형식이 잘못되었습니다") 
    String email,
    @NotBlank(message = "비밀번호는 필수값입니다") 
    String password
) {}
