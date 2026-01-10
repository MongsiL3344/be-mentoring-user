package io.github.mongsil3344.ikeeper.bementoring.user.dto;

import io.github.mongsil3344.ikeeper.bementoring.user.User;

/**
 * 유저 정보 응답 DTO
 * 이 DTO는 서버에서 만들어져서 클라이언트로 넘어가기 때문에 검증은 필요없고
 * 인자 순서 실수방지를 위해 정적 팩토리 메서드로 인스턴스를 생성해서 넘기도록 구현
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
