package com.biblioteca.horasjugadas.service;

import com.biblioteca.horasjugadas.client.JuegoClient;
import com.biblioteca.horasjugadas.client.UsuarioClient;
import com.biblioteca.horasjugadas.dto.HorasJugadasRequestDTO;
import com.biblioteca.horasjugadas.dto.HorasJugadasResponseDTO;
import com.biblioteca.horasjugadas.dto.JuegoDTO;
import com.biblioteca.horasjugadas.model.HorasJugadas;
import com.biblioteca.horasjugadas.repository.HorasJugadasRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HorasJugadasService {

    private final HorasJugadasRepository horasJugadasRepository;
    private final JuegoClient juegoClient;
    private final UsuarioClient usuarioClient;

    public List<HorasJugadasResponseDTO> obtenerTodas() {
        return horasJugadasRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Optional<HorasJugadasResponseDTO> obtenerPorId(Long id) {
        return horasJugadasRepository.findById(id).map(this::mapToDTO);
    }

    public List<HorasJugadasResponseDTO> obtenerPorUsuario(Long usuarioId) {
        validarUsuario(usuarioId);
        return horasJugadasRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<HorasJugadasResponseDTO> obtenerPorJuego(Long juegoId) {
        validarJuego(juegoId);
        return horasJugadasRepository.findByJuegoId(juegoId).stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Optional<HorasJugadasResponseDTO> obtenerPorUsuarioYJuego(Long usuarioId, Long juegoId) {
        validarUsuario(usuarioId);
        validarJuego(juegoId);
        return horasJugadasRepository.findByUsuarioIdAndJuegoId(usuarioId, juegoId)
                .map(this::mapToDTO);
    }

    @Transactional
    public HorasJugadasResponseDTO registrarHoras(HorasJugadasRequestDTO dto) {
        validarUsuario(dto.getUsuarioId());
        validarJuego(dto.getJuegoId());

        HorasJugadas registro = horasJugadasRepository
                .findByUsuarioIdAndJuegoId(dto.getUsuarioId(), dto.getJuegoId())
                .map(existente -> {
                    existente.setMinutosJugados(existente.getMinutosJugados() + dto.getMinutosJugados());
                    existente.setUltimaVezJugado(LocalDateTime.now());
                    return existente;
                })
                .orElseGet(() -> new HorasJugadas(
                        null,
                        dto.getUsuarioId(),
                        dto.getJuegoId(),
                        dto.getMinutosJugados(),
                        LocalDateTime.now()
                ));

        return mapToDTO(horasJugadasRepository.save(registro));
    }

    @Transactional
    public Optional<HorasJugadasResponseDTO> actualizarTotal(Long id, HorasJugadasRequestDTO dto) {
        validarUsuario(dto.getUsuarioId());
        validarJuego(dto.getJuegoId());

        return horasJugadasRepository.findById(id).map(registro -> {
            registro.setUsuarioId(dto.getUsuarioId());
            registro.setJuegoId(dto.getJuegoId());
            registro.setMinutosJugados(dto.getMinutosJugados());
            registro.setUltimaVezJugado(LocalDateTime.now());
            return mapToDTO(horasJugadasRepository.save(registro));
        });
    }

    @Transactional
    public void eliminar(Long id) {
        if (!horasJugadasRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe un registro de horas jugadas con id " + id);
        }
        horasJugadasRepository.deleteById(id);
    }

    private HorasJugadasResponseDTO mapToDTO(HorasJugadas registro) {
        JuegoDTO juego = obtenerJuegoSeguro(registro.getJuegoId());
        return new HorasJugadasResponseDTO(
                registro.getId(),
                registro.getUsuarioId(),
                registro.getJuegoId(),
                registro.getMinutosJugados(),
                registro.getMinutosJugados() / 60.0,
                registro.getUltimaVezJugado(),
                juego
        );
    }

    private void validarUsuario(Long usuarioId) {
        try {
            usuarioClient.obtenerUsuario(usuarioId);
        } catch (WebClientResponseException.NotFound e) {
            throw new IllegalArgumentException("No existe un usuario con id " + usuarioId);
        }
    }

    private void validarJuego(Long juegoId) {
        try {
            juegoClient.obtenerJuego(juegoId);
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("No existe un juego con id " + juegoId);
        }
    }

    private JuegoDTO obtenerJuegoSeguro(Long juegoId) {
        try {
            return juegoClient.obtenerJuego(juegoId);
        } catch (FeignException e) {
            return null;
        }
    }
}
