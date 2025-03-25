package fitnesstracker.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidInput(MethodArgumentNotValidException e,
                                                WebRequest webRequest) {
        return ResponseEntity.badRequest()
                .body(new CustomErrorMessage(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getLocalizedMessage(),
                        webRequest.getDescription(false),
                        LocalDateTime.now()
                ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleUniqueConstraintViolation(DataIntegrityViolationException  e,
                                                WebRequest webRequest) {
        return ResponseEntity.badRequest()
                .body(new CustomErrorMessage(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getLocalizedMessage(),
                        webRequest.getDescription(false),
                        LocalDateTime.now()
                ));
    }
}
