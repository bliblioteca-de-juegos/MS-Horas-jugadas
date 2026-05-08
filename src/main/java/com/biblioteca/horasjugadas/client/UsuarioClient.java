package com.biblioteca.horasjugadas.client;

import com.biblioteca.horasjugadas.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class UsuarioClient {

    private final WebClient usuarioWebClient;

    public UsuarioDTO obtenerUsuario(Long usuarioId) {
        return usuarioWebClient.get()
                .uri("/api/usuarios/{id}", usuarioId)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
    }
}
