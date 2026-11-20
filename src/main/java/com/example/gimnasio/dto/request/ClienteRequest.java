package com.example.gimnasio.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para registrar un cliente")
public class ClienteRequest {

    @Schema(description = "Nombre del cliente", example = "Juan Pérez")
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String nombre;

    @Schema(description = "Documento de identidad del cliente", example = "12345678")
    @NotBlank(message = "El documento es obligatorio")
    @Size(max = 50, message = "El documento no puede superar 50 caracteres")
    String documento;

    @Schema(description = "Indica si el cliente está activo", example = "true")
    @NotNull(message = "El estado activo es obligatorio")
    Boolean activo;

    public ClienteRequest() {
    }

    public ClienteRequest(String nombre, String documento, Boolean activo) {
        this.nombre = nombre;
        this.documento = documento;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
