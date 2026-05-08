CREATE TABLE horas_jugadas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    juego_id BIGINT NOT NULL,
    minutos_jugados INT NOT NULL,
    ultima_vez_jugado DATETIME,
    CONSTRAINT uk_horas_usuario_juego UNIQUE (usuario_id, juego_id)
);
