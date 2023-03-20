package br.com.medprime.paciente;

public record PacienteVisualizarSimpDto(Long id, String nome, String email, String cpf) {
    public PacienteVisualizarSimpDto(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
