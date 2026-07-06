package com.biblioteca.horasjugadas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.horasjugadas.dto.HorasJugadasRequestDTO;
import com.biblioteca.horasjugadas.dto.HorasJugadasResponseDTO;
import com.biblioteca.horasjugadas.service.HorasJugadasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Horas jugadas", description = "Operaciones de horas jugadas por usuario y juego")
public class HorasJugadasController {
    @Autowired
    private HorasJugadasService horasJugadasService;
    @GetMapping
    @Operation(summary = "Listar todos los registros de horas jugadas")
    public List<HorasJugadasResponseDTO> obtenerTodas() {
        return horasJugadasService.obtenerTodas();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener horas jugadas por ID")
    public ResponseEntity<HorasJugadasResponseDTO> obtenerPorId(@PathVariable Long id) {
        return horasJugadasService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar horas jugadas por usuario")
    public List<HorasJugadasResponseDTO> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return horasJugadasService.obtenerPorUsuario(usuarioId);
    }
    @GetMapping("/juego/{juegoId}")
    @Operation(summary = "Listar horas jugadas por juego")
    public List<HorasJugadasResponseDTO> obtenerPorJuego(@PathVariable Long juegoId) {
        return horasJugadasService.obtenerPorJuego(juegoId);
    }
    @GetMapping("/usuario/{usuarioId}/juego/{juegoId}")
    @Operation(summary = "Obtener horas de un usuario en un juego")
    public ResponseEntity<HorasJugadasResponseDTO> obtenerPorUsuarioYJuego(
            @PathVariable Long usuarioId,
            @PathVariable Long juegoId) {
        return horasJugadasService.obtenerPorUsuarioYJuego(usuarioId, juegoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @Operation(summary = "Registrar horas jugadas")
    public ResponseEntity<HorasJugadasResponseDTO> registrarHoras(
            @Valid @RequestBody HorasJugadasRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(horasJugadasService.registrarHoras(dto));
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un registro de horas jugadas")
    public ResponseEntity<HorasJugadasResponseDTO> actualizarTotal(
            @PathVariable Long id,
            @Valid @RequestBody HorasJugadasRequestDTO dto) {
        return horasJugadasService.actualizarTotal(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro de horas jugadas")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        horasJugadasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
