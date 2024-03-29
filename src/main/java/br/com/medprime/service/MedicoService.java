package br.com.medprime.service;

import br.com.medprime.endereco.Endereco;
import br.com.medprime.medico.*;
import br.com.medprime.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrar(MedicoCadastroDto mcd){
        var endereco = new Endereco(mcd.endereco().logradouro(),mcd.endereco().numero(),mcd.endereco().complemento(),
                mcd.endereco().bairro(),mcd.endereco().cidade(),mcd.endereco().uf(),mcd.endereco().cep());
        var medico = new Medico(null,mcd.nome(),mcd.email(),mcd.telefone(),mcd.crm(),mcd.especialidade(),endereco,true);
        return this.medicoRepository.save(medico);
    }

    @Transactional
    public Medico atualizar(MedicoAtualizarDto medicoAtualizarDto) {
        var medico = this.medicoRepository.getReferenceById(medicoAtualizarDto.id());

        if(medicoAtualizarDto.nome() != null) {
            medico.setNome(medicoAtualizarDto.nome());
        }
        if(medicoAtualizarDto.telefone() != null) {
            medico.setTelefone(medicoAtualizarDto.telefone());
        }
        if(medicoAtualizarDto.endereco() != null){
            if(medicoAtualizarDto.endereco().logradouro() != null) {
                medico.getEndereco().setLogradouro(medicoAtualizarDto.endereco().logradouro());
            }
            if(medicoAtualizarDto.endereco().numero() != null) {
                medico.getEndereco().setNumero(medicoAtualizarDto.endereco().numero());
            }
            if(medicoAtualizarDto.endereco().complemento() != null) {
                medico.getEndereco().setComplemento(medicoAtualizarDto.endereco().complemento());
            }
            if(medicoAtualizarDto.endereco().bairro() != null) {
                medico.getEndereco().setBairro(medicoAtualizarDto.endereco().bairro());
            }
            if(medicoAtualizarDto.endereco().cidade() != null) {
                medico.getEndereco().setCidade(medicoAtualizarDto.endereco().cidade());
            }
            if(medicoAtualizarDto.endereco().uf() != null) {
                medico.getEndereco().setUf(medicoAtualizarDto.endereco().uf());
            }
            if(medicoAtualizarDto.endereco().cep() != null) {
                medico.getEndereco().setCep(medicoAtualizarDto.endereco().cep());
            }
        }
        return medico;
    }

    public Page<VisualizarMedicoSimpDto> listar(Pageable pageable){
        return this.medicoRepository.findAllByAtivo(pageable).map(VisualizarMedicoSimpDto::new);
    }

    public Medico buscarPorId(Long id){
        Optional<Medico> medico = this.medicoRepository.findByIdAndAtivo(id);
        if(!medico.isPresent()){
            throw new EntityNotFoundException();
        }
        return medico.get();
    }

    @Transactional
    public void desativar(Long id){
        var medico = this.medicoRepository.getReferenceById(id);
        medico.setAtivo(false);
    }

}
