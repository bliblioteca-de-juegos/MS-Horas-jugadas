package com.biblioteca.horasjugadas.service;

import com.biblioteca.horasjugadas.client.JuegoClient;
import com.biblioteca.horasjugadas.client.UsuarioClient;
import com.biblioteca.horasjugadas.dto.HorasJugadasResponseDTO;
import com.biblioteca.horasjugadas.model.HorasJugadas;
import com.biblioteca.horasjugadas.repository.HorasJugadasRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HorasJugadasServiceTest {

    @Mock
    private HorasJugadasRepository horasJugadasRepository;
    @Mock
    private JuegoClient juegoClient;
    @Mock
    private UsuarioClient usuarioClient;
    @InjectMocks
    private HorasJugadasService horasJugadasService;

    private final Faker faker = new Faker();

    @Test
    void obtenerPorIdConvierteMinutosAHoras() {
        Long id = faker.number().numberBetween(1L, 1000L);
        HorasJugadas registro = new HorasJugadas(id, 10L, 20L, 150, LocalDateTime.now());
        when(horasJugadasRepository.findById(id)).thenReturn(Optional.of(registro));

        Optional<HorasJugadasResponseDTO> resultado = horasJugadasService.obtenerPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(150, resultado.get().minutosJugados());
        assertEquals(2.5, resultado.get().horasJugadas());
    }

    @Test
    void eliminarLanzaExcepcionCuandoElRegistroNoExiste() {
        Long id = faker.number().numberBetween(1L, 1000L);
        when(horasJugadasRepository.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> horasJugadasService.eliminar(id));
        verify(horasJugadasRepository, never()).deleteById(id);
    }
}
