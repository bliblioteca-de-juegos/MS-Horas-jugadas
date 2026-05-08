package com.biblioteca.horasjugadas.dto;

public record JuegoDTO(
        Long id,
        String nombre,
        String titulo,
        String descripcion,
        Double precio
) {
}
