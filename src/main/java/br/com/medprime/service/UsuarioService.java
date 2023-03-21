package br.com.medprime.service;

import br.com.medprime.repository.UsuarioRepository;
import br.com.medprime.usuario.Usuario;
import br.com.medprime.usuario.UsuarioCLDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario cadastrar(UsuarioCLDto usuarioCLDto){
        return this.usuarioRepository.save(new Usuario(null, usuarioCLDto.login()
                ,this.passwordEncoder.encode(usuarioCLDto.senha())));
    }
}
