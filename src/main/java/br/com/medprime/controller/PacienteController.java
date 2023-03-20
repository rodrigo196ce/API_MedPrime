package br.com.medprime.controller;

import br.com.medprime.paciente.PacienteAtualizarDto;
import br.com.medprime.paciente.PacienteCadastrarDto;
import br.com.medprime.paciente.PacienteVisualizarDto;
import br.com.medprime.paciente.PacienteVisualizarSimpDto;
import br.com.medprime.service.PacienteService;
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
@RequestMapping(value = "paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @RequestMapping(value = "cadastrar",method = RequestMethod.POST)
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PacienteCadastrarDto pacienteCadastrarDto
            ,UriComponentsBuilder uriBuilder){
        var paciente = this.pacienteService.cadastrar(pacienteCadastrarDto);
        var uri = uriBuilder.path("paciente/cadastrar/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteVisualizarDto(paciente));
    }

    @RequestMapping(value = "atualizar",method = RequestMethod.PUT)
    public ResponseEntity<PacienteVisualizarDto> atualizar(@RequestBody @Valid PacienteAtualizarDto pacienteAtualizarDto){
        var paciente = this.pacienteService.atualizar(pacienteAtualizarDto);
        return ResponseEntity.ok(new PacienteVisualizarDto(paciente));
    }

    @RequestMapping(value = "listar",method = RequestMethod.GET)
    public ResponseEntity<Page<PacienteVisualizarSimpDto>> listar
            (@PageableDefault(page = 0,size = 10,sort = "nome",direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(this.pacienteService.listar(pageable).map(PacienteVisualizarSimpDto::new));
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<PacienteVisualizarDto> buscarPorId(@PathVariable("id")Long id){
        var paciente = this.pacienteService.buscarPorId(id);
        return ResponseEntity.ok(new PacienteVisualizarDto(paciente));
    }

    @RequestMapping(value = "desativar/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> desativar(@PathVariable("id")Long id){
        this.pacienteService.desativar(id);
        return ResponseEntity.ok().build();
    }


}
