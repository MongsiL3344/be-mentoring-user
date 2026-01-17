package io.github.mongsil3344.ikeeper.bementoring.userserver.error;

import io.github.mongsil3344.ikeeper.bementoring.userserver.error.exception.ConflictException;
import io.github.mongsil3344.ikeeper.bementoring.userserver.error.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리 핸들러
 * 모든 컨트롤러에서 발생하는 예외를 잡아서 통일된 형식으로 응답
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * ============ 커스텀 예외 핸들러 ================
     * exception/ 디렉토리에 정의된 커스텀 예외 클래스를 파라미터로 받아서 
     * 해당 예외가 발생했을시 여기로 매핑됨
     */

    /**
     * 404 NotFoundException
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        log.warn("Resource not found: {}", e.getMessage());

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    /**
     * 409 ConflictException
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException e) {
        log.warn("Resource conflict: {}", e.getMessage());

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse.of(HttpStatus.CONFLICT, e.getMessage()));
    }

    /**
     * ============ Validation에서 발생하는 예외 핸들러 ================
     * 컨트롤러에서 Valid가 실패할 경우 여기로 매핑됨
     */

    /**
     * @Valid 검증 실패 - @RequestBody (400 Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));

        log.warn("Validation failed: {}", errorMessage);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of(HttpStatus.BAD_REQUEST, errorMessage));
    }

    /**
     * @RequestParam, @PathVariable 검증 실패 (400 Bad Request)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations().stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .collect(Collectors.joining(", "));

        log.warn("Constraint violation: {}", errorMessage);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of(HttpStatus.BAD_REQUEST, errorMessage));
    }

    /**
     * 필수 요청 헤더 누락 (400 Bad Request)
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestHeader(MissingRequestHeaderException e) {
        String errorMessage = "필수 헤더가 누락되었습니다: " + e.getHeaderName();

        log.warn("Missing request header: {}", e.getHeaderName());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of(HttpStatus.BAD_REQUEST, errorMessage));
    }

    /**
     * ============ 그 외 예상치 못한 예외 핸들러 ================
     * 그 외 예상치 못한 예외가 발생했을 경우 여기로 매핑됨
     */

    /**
     * 그 외 예상치 못한 예외 (500 Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unexpected error occurred", e);

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다"));
    }
}
