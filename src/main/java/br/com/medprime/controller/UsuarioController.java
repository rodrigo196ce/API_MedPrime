package br.com.medprime.controller;

import br.com.medprime.infra.security.DadosTokenJwtDto;
import br.com.medprime.infra.security.TokenService;
import br.com.medprime.service.AuthenticationService;
import br.com.medprime.service.UsuarioService;
import br.com.medprime.usuario.Usuario;
import br.com.medprime.usuario.UsuarioCLDto;
import br.com.medprime.usuario.UsuarioVisualizarDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody @Valid UsuarioCLDto usuarioCLDto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioCLDto.login(),usuarioCLDto.senha());
        var authetication = this.authenticationManager.authenticate(authenticationToken);
        var tokenJWT = this.tokenService.gerarToken((Usuario) authetication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwtDto(tokenJWT));
    }

    @RequestMapping(value = "cadastrar",method = RequestMethod.POST)
    public ResponseEntity<UsuarioVisualizarDto> cadastrar(@RequestBody @Valid UsuarioCLDto usuarioCLDto
            ,UriComponentsBuilder uriBuilder){
        var usuario = this.usuarioService.cadastrar(usuarioCLDto);
        var uri = uriBuilder.path("usuario/cadastrar/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioVisualizarDto(usuario));
    }

}
