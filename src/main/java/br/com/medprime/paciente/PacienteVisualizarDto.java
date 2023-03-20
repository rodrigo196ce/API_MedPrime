package br.com.medprime.paciente;

import br.com.medprime.endereco.EnderecoVisualizarDto;

public record PacienteVisualizarDto(Long id, String nome, String email, String telefone, String cpf
        ,EnderecoVisualizarDto endereco) {
    public PacienteVisualizarDto(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(),
                new EnderecoVisualizarDto(paciente.getEndereco()));
    }
}
