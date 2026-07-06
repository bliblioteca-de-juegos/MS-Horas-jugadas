package com.biblioteca.horasjugadas.client;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.horasjugadas.dto.UsuarioDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
@Component
public class UsuarioClient {
    @Autowired
    private WebClient usuarioWebClient;
    public UsuarioDTO obtenerUsuario(Long usuarioId) {
        return usuarioWebClient.get()
                .uri("/api/v2/usuarios/{id}", usuarioId)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
    }
}
