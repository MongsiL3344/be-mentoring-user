package io.github.mongsil3344.ikeeper.bementoring.userserver.error.exception;

/**
 * 리소스 충돌(중복) 시 발생하는 예외
 * HTTP 409 Conflict로 변환됨
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
