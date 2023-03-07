package br.com.medprime.infra.exception;

import org.springframework.validation.FieldError;

public record ValidExceptionCustom(String campo, String mensagem) {

    public ValidExceptionCustom(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
