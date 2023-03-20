package br.com.medprime.service;

import br.com.medprime.endereco.Endereco;
import br.com.medprime.medico.Medico;
import br.com.medprime.paciente.Paciente;
import br.com.medprime.paciente.PacienteAtualizarDto;
import br.com.medprime.paciente.PacienteCadastrarDto;
import br.com.medprime.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrar(PacienteCadastrarDto pcd){
        var endereco = new Endereco(pcd.endereco().logradouro(),pcd.endereco().numero(),pcd.endereco().complemento()
                ,pcd.endereco().bairro(),pcd.endereco().cidade(),pcd.endereco().uf(),pcd.endereco().cep());
        var paciente = new Paciente(null,pcd.nome(),pcd.email(),pcd.telefone(),pcd.cpf(),endereco);
        return this.pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente atualizar(PacienteAtualizarDto pad){
        var paciente = this.pacienteRepository.getReferenceById(pad.id());

        if (pad.nome() != null){
            paciente.setNome(pad.nome());
        }
        if (pad.telefone() != null){
            paciente.setTelefone(pad.telefone());
        }
        if (pad.endereco() != null){
            if (pad.endereco().logradouro() != null) {
                paciente.getEndereco().setLogradouro(pad.endereco().logradouro());
            }
            if (pad.endereco().numero() != null) {
                paciente.getEndereco().setNumero(pad.endereco().numero());
            }
            if (pad.endereco().complemento() != null) {
                paciente.getEndereco().setComplemento(pad.endereco().complemento());
            }
            if(pad.endereco().bairro() != null) {
                paciente.getEndereco().setBairro(pad.endereco().bairro());
            }
            if (pad.endereco().cidade() != null) {
                paciente.getEndereco().setCidade(pad.endereco().cidade());
            }
            if (pad.endereco().uf() != null) {
                paciente.getEndereco().setUf(pad.endereco().uf());
            }
            if (pad.endereco().cep() != null) {
                paciente.getEndereco().setCep(pad.endereco().cep());
            }
        }
        return paciente;
    }

    public Page<Paciente> listar(Pageable pageable){
        return this.pacienteRepository.findAll(pageable);
    }

}
