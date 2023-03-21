package br.com.medprime.infra.security;

import br.com.medprime.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try{
            var algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                    .withIssuer("API medprime")
                    .withSubject(usuario.getLogin())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        }catch(JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token JWT",e);
        }
    }

    public String getSubject(String tokenJwt){
        try{
            var algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer("API medprime")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        }catch(JWTVerificationException e){
            throw new RuntimeException("Token JWT expirado ou inválido",e);
        }
    }

    public Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
