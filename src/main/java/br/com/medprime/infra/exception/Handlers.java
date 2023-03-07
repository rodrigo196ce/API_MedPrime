package br.com.medprime.infra.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class Handlers {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidExceptionCustom>> methodArgumentNotValidException(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidExceptionCustom::new).toList());
    }

}
