package com.example.gimnasio.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Información de un cliente")
public class ClienteResponse {

    @Schema(example = "1")
    Long id;

    @Schema(example = "Juan Pérez")
    String nombre;

    @Schema(example = "12345678")
    String documento;

    @Schema(example = "true")
    boolean activo;

    @Schema(description = "Rutinas asignadas al cliente")
    List<RutinaResumenResponse> rutinas;

    public ClienteResponse() {
    }

    public ClienteResponse(Long id, String nombre, String documento, boolean activo,
                           List<RutinaResumenResponse> rutinas) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.activo = activo;
        this.rutinas = rutinas;
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

    public List<RutinaResumenResponse> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<RutinaResumenResponse> rutinas) {
        this.rutinas = rutinas;
    }
}
