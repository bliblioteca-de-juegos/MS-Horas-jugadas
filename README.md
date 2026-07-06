# MS-Horas-jugadas

Microservicio encargado de registrar y consultar las horas o minutos jugados por usuario y juego.

## Responsabilidad

- Crear registros de tiempo jugado.
- Consultar horas por usuario.
- Consultar horas por juego.
- Consultar horas por usuario y juego.
- Actualizar y eliminar registros.
- Validar existencia de usuario y juego antes de guardar.

## Datos tecnicos

| Item | Valor |
| --- | --- |
| Puerto | `8084` |
| Base de datos | `horas_jugadas_db` |
| Ruta base | `/api/v2/horas-jugadas` |
| Swagger | `http://localhost:8084/doc/swagger-ui.html` |
| Eureka name | `ms-horas-jugadas` |

## Endpoints principales

- `GET /api/v2/horas-jugadas`
- `GET /api/v2/horas-jugadas/{id}`
- `GET /api/v2/horas-jugadas/usuario/{usuarioId}`
- `GET /api/v2/horas-jugadas/juego/{juegoId}`
- `GET /api/v2/horas-jugadas/usuario/{usuarioId}/juego/{juegoId}`
- `POST /api/v2/horas-jugadas`
- `PUT /api/v2/horas-jugadas/{id}`
- `DELETE /api/v2/horas-jugadas/{id}`

## Comunicacion

- Usa Feign Client para consultar juegos en `ms-juegos`.
- Usa WebClient para consultar usuarios en `ms-usuario`.
- Se registra en Eureka.

## Ejecucion local

```bash
./mvnw spring-boot:run
```

## Ejecucion con Docker

Desde la repo `Infraestructura`:

```bash
docker compose up -d --build ms-horas-jugadas
```

