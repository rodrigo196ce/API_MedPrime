package br.com.medprime.controller;

import br.com.medprime.medico.MedicoAtualizarDto;
import br.com.medprime.medico.MedicoCadastroDto;
import br.com.medprime.medico.VisualizarMedicoDto;
import br.com.medprime.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @RequestMapping(value = "cadastrar",method = RequestMethod.POST)
    public ResponseEntity<VisualizarMedicoDto> cadastrar(@RequestBody @Valid MedicoCadastroDto medicoCadastroDto
                                                         ,UriComponentsBuilder uriBuilder){
        var medico = this.medicoService.cadastrar(medicoCadastroDto);
        var uri = uriBuilder.path("medico/cadastrar/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new VisualizarMedicoDto(medico));
    }

    @RequestMapping(value = "atualizar",method = RequestMethod.PUT)
    public ResponseEntity<VisualizarMedicoDto> atualizar(@RequestBody @Valid MedicoAtualizarDto medicoAtualizarDto){
        var medico = this.medicoService.atualizar(medicoAtualizarDto);
        return ResponseEntity.ok(new VisualizarMedicoDto(medico));
    }

}
