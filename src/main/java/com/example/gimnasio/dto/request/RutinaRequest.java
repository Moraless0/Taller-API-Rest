package com.example.gimnasio.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para registrar una rutina")
public class RutinaRequest {

    @Schema(description = "Nombre de la rutina", example = "Entrenamiento funcional")
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String nombre;

    @Schema(description = "Nivel de la rutina", example = "BASICO")
    @NotBlank(message = "El nivel es obligatorio")
    @Pattern(regexp = "BASICO|INTERMEDIO|AVANZADO", message = "El nivel debe ser BASICO, INTERMEDIO o AVANZADO")
    @Size(max = 20, message = "El nivel no puede superar 20 caracteres")
    String nivel;

    public RutinaRequest() {
    }

    public RutinaRequest(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
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
