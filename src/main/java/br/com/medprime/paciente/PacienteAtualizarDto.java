package br.com.medprime.paciente;

import br.com.medprime.endereco.EnderecoAtualizarDto;
import jakarta.validation.constraints.NotNull;

public record PacienteAtualizarDto(@NotNull Long id, String nome, String telefone, EnderecoAtualizarDto endereco) {
}
