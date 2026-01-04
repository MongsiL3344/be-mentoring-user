package io.github.mongsil3344.ikeeper.bementoring.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String email;
}
