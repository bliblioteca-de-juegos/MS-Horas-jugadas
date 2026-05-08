package com.biblioteca.horasjugadas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "horas_jugadas",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "juego_id"})
)
public class HorasJugadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "juego_id", nullable = false)
    private Long juegoId;

    @Column(name = "minutos_jugados", nullable = false)
    private Integer minutosJugados;

    @Column(name = "ultima_vez_jugado")
    private LocalDateTime ultimaVezJugado;
}
