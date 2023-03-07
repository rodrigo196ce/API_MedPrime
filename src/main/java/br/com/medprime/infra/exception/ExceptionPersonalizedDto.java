package br.com.medprime.infra.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ExceptionPersonalizedDto {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String campo;
    private String message;
    private String path;

    public ExceptionPersonalizedDto(LocalDateTime timestamp, int status, String error, String message, String path){
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public ExceptionPersonalizedDto(FieldError fieldError){
        this.campo = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }


}
