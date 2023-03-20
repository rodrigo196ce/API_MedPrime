package br.com.medprime.medico;

import br.com.medprime.endereco.Endereco;

public record VisualizarMedicoDto(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco) {

    public VisualizarMedicoDto(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(),
                medico.getEspecialidade(),medico.getEndereco());
    }

}


