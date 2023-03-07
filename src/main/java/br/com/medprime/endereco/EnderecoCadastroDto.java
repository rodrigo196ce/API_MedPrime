package br.com.medprime.endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoCadastroDto(
        @NotBlank
        String logradouro,
        String numero,
        String complemento,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @NotBlank
        String cep){}


