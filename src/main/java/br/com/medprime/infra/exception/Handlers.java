package br.com.medprime.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ExceptionCustomDto> entityNotFoundException(EntityNotFoundException e, HttpServletRequest request){
        return new ResponseEntity<>(new ExceptionCustomDto(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),
                "ID não encontrado", request.getRequestURI()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException e, HttpServletRequest request){
        return new ResponseEntity<>(new ExceptionCustomDto(LocalDateTime.now(),HttpStatus.FORBIDDEN.value(),
                "Usuário inexistente ou senha inválida", request.getRequestURI()),HttpStatus.FORBIDDEN);
    }

}
