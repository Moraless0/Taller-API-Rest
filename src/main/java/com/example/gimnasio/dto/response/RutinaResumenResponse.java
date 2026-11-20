package com.example.gimnasio.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resumen de una rutina")
public class RutinaResumenResponse {

    @Schema(example = "1")
    Long id;

    @Schema(example = "Entrenamiento funcional")
    String nombre;

    @Schema(example = "BASICO")
    String nivel;

    public RutinaResumenResponse() {
    }

    public RutinaResumenResponse(Long id, String nombre, String nivel) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
