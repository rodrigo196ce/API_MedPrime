package br.com.medprime.infra.exception;

import java.time.LocalDateTime;

public record ExceptionCustomDto(LocalDateTime timestamp, int status, String message, String path) {}

