package com.biblioteca.horasjugadas.repository;

import com.biblioteca.horasjugadas.model.HorasJugadas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HorasJugadasRepository extends JpaRepository<HorasJugadas, Long> {

    List<HorasJugadas> findByUsuarioId(Long usuarioId);

    List<HorasJugadas> findByJuegoId(Long juegoId);

    Optional<HorasJugadas> findByUsuarioIdAndJuegoId(Long usuarioId, Long juegoId);
}
