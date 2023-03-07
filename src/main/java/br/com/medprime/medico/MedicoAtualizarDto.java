package br.com.medprime.medico;

import br.com.medprime.endereco.EnderecoAtualizarDto;
import jakarta.validation.constraints.NotNull;

public record MedicoAtualizarDto(@NotNull Long id, String nome, String telefone, EnderecoAtualizarDto endereco) {}

