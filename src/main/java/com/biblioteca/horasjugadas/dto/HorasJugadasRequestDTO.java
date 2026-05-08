package com.biblioteca.horasjugadas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HorasJugadasRequestDTO {

    @NotNull(message = "El usuarioId es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El juegoId es obligatorio")
    private Long juegoId;

    @NotNull(message = "Los minutosJugados son obligatorios")
    @Min(value = 1, message = "Los minutos jugados deben ser mayores a 0")
    private Integer minutosJugados;
}
