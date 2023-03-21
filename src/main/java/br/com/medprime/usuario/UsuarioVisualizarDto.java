package br.com.medprime.usuario;

public record UsuarioVisualizarDto(Long id, String login) {
    public UsuarioVisualizarDto(Usuario usuario){
        this(usuario.getId(), usuario.getLogin());
    }
}
