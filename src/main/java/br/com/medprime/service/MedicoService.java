package br.com.medprime.service;

import br.com.medprime.endereco.Endereco;
import br.com.medprime.medico.Medico;
import br.com.medprime.medico.MedicoCadastroDto;
import br.com.medprime.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrar(MedicoCadastroDto mcd){
        var endereco = new Endereco(mcd.endereco().logradouro(),mcd.endereco().numero(),mcd.endereco().complemento(),
                mcd.endereco().bairro(),mcd.endereco().cidade(),mcd.endereco().uf(),mcd.endereco().cep());
        var medico = new Medico(null,mcd.nome(),mcd.email(),mcd.telefone(),mcd.crm(),mcd.especialidade(),endereco);
        return this.medicoRepository.save(medico);
    }

}
