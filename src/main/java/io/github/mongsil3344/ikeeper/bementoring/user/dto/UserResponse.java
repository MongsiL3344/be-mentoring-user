package io.github.mongsil3344.ikeeper.bementoring.user.dto;

import io.github.mongsil3344.ikeeper.bementoring.user.User;

/**
 * 유저 정보 응답 DTO (Record)
 */
public record UserResponse(Long id, String name, String email) {

    /**
     * UserResponse DTO를 생성하는 정적 팩토리 메서드
     * @param user User 엔티티 객체
     * @return UserResponse DTO
     */
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
