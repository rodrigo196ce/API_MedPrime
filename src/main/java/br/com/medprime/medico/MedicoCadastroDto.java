package br.com.medprime.medico;

import br.com.medprime.endereco.Endereco;
import br.com.medprime.endereco.EnderecoCadastroDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoCadastroDto(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank @Pattern(regexp = "^\\d{11}$", message = "Número de telefone inválido.")
        String telefone,
        @NotBlank
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        EnderecoCadastroDto endereco) {}


