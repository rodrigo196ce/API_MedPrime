package br.com.medprime.medico;

public record VisualizarMedicoSimpDto(Long id,String nome, String email, String crm, Especialidade especialidade) {
    public VisualizarMedicoSimpDto(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
