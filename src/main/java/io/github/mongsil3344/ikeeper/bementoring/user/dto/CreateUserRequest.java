package io.github.mongsil3344.ikeeper.bementoring.user.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 생성 요청 DTO
 */
@Getter
@Setter
public class CreateUserRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
