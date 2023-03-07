package br.com.medprime.endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoAtualizarDto(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String cep) {}


