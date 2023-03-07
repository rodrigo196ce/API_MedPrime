package br.com.medprime.medico;

import br.com.medprime.endereco.Endereco;
import br.com.medprime.endereco.EnderecoCadastroDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicoCadastroDto(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        EnderecoCadastroDto endereco) {}


