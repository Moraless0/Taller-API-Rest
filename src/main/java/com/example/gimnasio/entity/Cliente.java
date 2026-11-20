package com.example.gimnasio.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 50)
    private String documento;

    @Column(nullable = false)
    private boolean activo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cliente_rutina",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "rutina_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"cliente_id", "rutina_id"})
    )
    private List<Rutina> rutinas = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nombre, String documento, boolean activo) {
        this.nombre = nombre;
        this.documento = documento;
        this.activo = activo;
    }

    public void asignarRutina(Rutina rutina) {
        if (!rutinas.contains(rutina)) {
            rutinas.add(rutina);
        }
        if (!rutina.getClientes().contains(this)) {
            rutina.getClientes().add(this);
        }
    }

    public void quitarRutina(Rutina rutina) {
        rutinas.remove(rutina);
        rutina.getClientes().remove(this);
    }

    public Long getId() {
        return id;
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

    public List<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<Rutina> rutinas) {
        this.rutinas = rutinas;
    }
}
