package com.biblioteca.horasjugadas.client;

import com.biblioteca.horasjugadas.dto.JuegoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-juegos")
public interface JuegoClient {

    @GetMapping("/api/v2/juegos/{id}")
    JuegoDTO obtenerJuego(@PathVariable Long id);
}
