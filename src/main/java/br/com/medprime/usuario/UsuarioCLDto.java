package br.com.medprime.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioCLDto(@NotBlank String login, @NotBlank String senha) {}

