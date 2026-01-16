package io.github.mongsil3344.ikeeper.bementoring.userserver.error;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

/**
 * 통일된 에러 응답 DTO
 * 모든 예외 상황에서 동일한 형식으로 응답
 */
public record ErrorResponse(
    boolean success,
    int code,
    String message,
    LocalDateTime timestamp
) {
    public static ErrorResponse of(HttpStatus status, String message) {
        return new ErrorResponse(false, status.value(), message, LocalDateTime.now());
    }
}
