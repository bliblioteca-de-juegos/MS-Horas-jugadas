package com.biblioteca.horasjugadas.dto;

import java.time.LocalDateTime;

public record HorasJugadasResponseDTO(
        Long id,
        Long usuarioId,
        Long juegoId,
        Integer minutosJugados,
        Double horasJugadas,
        LocalDateTime ultimaVezJugado,
        JuegoDTO juego
) {
}
