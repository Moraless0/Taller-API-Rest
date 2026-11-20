package com.example.gimnasio.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resumen de un cliente")
public class ClienteResumenResponse {

    @Schema(example = "1")
    Long id;

    @Schema(example = "Juan Pérez")
    String nombre;

    @Schema(example = "12345678")
    String documento;

    @Schema(example = "true")
    boolean activo;

    public ClienteResumenResponse() {
    }

    public ClienteResumenResponse(Long id, String nombre, String documento, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
