package br.com.medprime.controller;

import br.com.medprime.medico.MedicoAtualizarDto;
import br.com.medprime.medico.MedicoCadastroDto;
import br.com.medprime.medico.VisualizarMedicoDto;
import br.com.medprime.medico.VisualizarMedicoSimpDto;
import br.com.medprime.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "listar", method = RequestMethod.GET)
    public ResponseEntity<Page<VisualizarMedicoSimpDto>> listar
            (@PageableDefault(size = 10, page = 0, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(this.medicoService.listar(pageable));
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id")Long id){
        var medico = this.medicoService.buscarPorId(id);
        return ResponseEntity.ok(new VisualizarMedicoSimpDto(medico));
    }

    @RequestMapping(value = "desativar/{id}")
    public ResponseEntity<?> desativar(@PathVariable("id")Long id){
        this.medicoService.desativar(id);
        return ResponseEntity.ok().build();
    }

}
