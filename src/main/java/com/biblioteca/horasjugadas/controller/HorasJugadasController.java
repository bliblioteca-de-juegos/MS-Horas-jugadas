package com.biblioteca.horasjugadas.controller;

import com.biblioteca.horasjugadas.dto.HorasJugadasRequestDTO;
import com.biblioteca.horasjugadas.dto.HorasJugadasResponseDTO;
import com.biblioteca.horasjugadas.service.HorasJugadasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/horas-jugadas")
@RequiredArgsConstructor
public class HorasJugadasController {

    private final HorasJugadasService horasJugadasService;

    @GetMapping
    public List<HorasJugadasResponseDTO> obtenerTodas() {
        return horasJugadasService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorasJugadasResponseDTO> obtenerPorId(@PathVariable Long id) {
        return horasJugadasService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<HorasJugadasResponseDTO> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return horasJugadasService.obtenerPorUsuario(usuarioId);
    }

    @GetMapping("/juego/{juegoId}")
    public List<HorasJugadasResponseDTO> obtenerPorJuego(@PathVariable Long juegoId) {
        return horasJugadasService.obtenerPorJuego(juegoId);
    }

    @GetMapping("/usuario/{usuarioId}/juego/{juegoId}")
    public ResponseEntity<HorasJugadasResponseDTO> obtenerPorUsuarioYJuego(
            @PathVariable Long usuarioId,
            @PathVariable Long juegoId) {
        return horasJugadasService.obtenerPorUsuarioYJuego(usuarioId, juegoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HorasJugadasResponseDTO> registrarHoras(
            @Valid @RequestBody HorasJugadasRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(horasJugadasService.registrarHoras(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorasJugadasResponseDTO> actualizarTotal(
            @PathVariable Long id,
            @Valid @RequestBody HorasJugadasRequestDTO dto) {
        return horasJugadasService.actualizarTotal(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        horasJugadasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
