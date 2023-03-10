package com.scascanner.studycafe.web.login.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice //모든 Controller가 실행될 때 사전에 실행됨
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = getExceptionResponse(ex, request);

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = getExceptionResponse(ex, request);

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnMatchedPasswordException.class)
    public final ResponseEntity<Object> UnMatchedPasswordException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = getExceptionResponse(ex, request);

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse getExceptionResponse(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false)).build();
        return exceptionResponse;
    }

    /**
     * 예외 처리를 할 경우에 보낼 DTO
     */
    @Builder
    @Getter
    static class ExceptionResponse{
        private LocalDateTime timestamp;
        private String message;
        private String details;
    }
}
