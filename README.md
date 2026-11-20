# API REST Gestión de Gimnasio

API REST desarrollada con Spring Boot para administrar clientes y rutinas de un gimnasio, incluyendo una relación muchos a muchos entre ambas entidades.

## Tecnologías

- Java 21
- Spring Boot 4.1.2
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- MySQL
- SpringDoc OpenAPI (Swagger UI)

## Configuración de base de datos

La aplicación usa MySQL en `localhost:3306` y la base de datos `gimnasio_db`.

1. Crear la base de datos en MySQL:

```sql
CREATE DATABASE gimnasio_db;
```

2. Editar `src/main/resources/application.properties` y colocar la contraseña del usuario `root`:

```properties
spring.datasource.password=tu_password
```

3. Hibernate actualizará las tablas automáticamente con `spring.jpa.hibernate.ddl-auto=update`.

## Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

La API queda disponible en:

```text
http://localhost:8080
```

Documentación Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

## Endpoints principales

### Clientes

- `POST /api/clientes`
- `GET /api/clientes`
- `GET /api/clientes/{id}`
- `PUT /api/clientes/{id}`
- `DELETE /api/clientes/{id}`

### Rutinas

- `POST /api/rutinas`
- `GET /api/rutinas`
- `GET /api/rutinas/{id}`
- `PUT /api/rutinas/{id}`
- `DELETE /api/rutinas/{id}`

### Relación Cliente - Rutina

- `POST /api/clientes/{clienteId}/rutinas/{rutinaId}`
- `GET /api/clientes/{clienteId}/rutinas`
- `DELETE /api/clientes/{clienteId}/rutinas/{rutinaId}`
- `GET /api/rutinas/{rutinaId}/clientes`

## Notas

- `DELETE /api/clientes/{id}` desactiva el cliente de forma lógica (`activo = false`).
- `GET /api/clientes` solo lista clientes activos.
- El campo `nivel` de una rutina debe ser `BASICO`, `INTERMEDIO` o `AVANZADO`.
- La tabla intermedia `cliente_rutina` se genera automáticamente por JPA.

## Video de sustentación

https://drive.google.com/file/d/1Olf3nXGFfnU2r-y2PR0OYZDO1OWrMe4w/view?usp=sharing
