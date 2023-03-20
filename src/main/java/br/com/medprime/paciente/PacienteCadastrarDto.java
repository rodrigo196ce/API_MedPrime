package br.com.medprime.paciente;

import br.com.medprime.endereco.EnderecoCadastroDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteCadastrarDto(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String telefone,
        @NotBlank @Pattern(regexp = "\\d{11}", message = "CPF inválido. Ex: 99999999999") String cpf,
        @NotNull @Valid EnderecoCadastroDto endereco) {}

