package com.example.gimnasio.service;

import com.example.gimnasio.dto.request.RutinaRequest;
import com.example.gimnasio.dto.response.ClienteResumenResponse;
import com.example.gimnasio.dto.response.RutinaResponse;
import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.entity.Rutina;
import com.example.gimnasio.repository.RutinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RutinaService {

    private final RutinaRepository rutinaRepository;

    public RutinaService(RutinaRepository rutinaRepository) {
        this.rutinaRepository = rutinaRepository;
    }

    public RutinaResponse crearRutina(RutinaRequest request) {

        if (rutinaRepository.existsByNombre(request.getNombre())) {
            throw new RuntimeException("Ya existe una rutina con ese nombre");
        }

        Rutina rutina = new Rutina(
                request.getNombre(),
                request.getNivel()
        );

        Rutina guardada = rutinaRepository.save(rutina);

        return convertirRutina(guardada);
    }

    @Transactional(readOnly = true)
    public List<RutinaResponse> listarRutinas() {

        return rutinaRepository.findAll()
                .stream()
                .map(this::convertirRutina)
                .toList();
    }

    @Transactional(readOnly = true)
    public RutinaResponse buscarRutina(Long id) {

        Rutina rutina = obtenerRutina(id);

        return convertirRutina(rutina);
    }

    public RutinaResponse actualizarRutina(Long id, RutinaRequest request) {

        Rutina rutina = obtenerRutina(id);

        if (rutinaRepository.existsByNombreAndIdNot(request.getNombre(), id)) {
            throw new RuntimeException("Ya existe una rutina con ese nombre");
        }

        rutina.setNombre(request.getNombre());
        rutina.setNivel(request.getNivel());

        Rutina actualizada = rutinaRepository.save(rutina);

        return convertirRutina(actualizada);
    }

    public void eliminarRutina(Long id) {

        Rutina rutina = obtenerRutina(id);

        for (Cliente cliente : new ArrayList<>(rutina.getClientes())) {
            cliente.quitarRutina(rutina);
        }

        rutinaRepository.delete(rutina);
    }

    @Transactional(readOnly = true)
    public List<ClienteResumenResponse> listarClientesDeRutina(Long rutinaId) {

        Rutina rutina = obtenerRutina(rutinaId);

        return rutina.getClientes()
                .stream()
                .map(this::convertirClienteResumen)
                .toList();
    }

    private Rutina obtenerRutina(Long id) {

        return rutinaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rutina no encontrada")
                );
    }

    private RutinaResponse convertirRutina(Rutina rutina) {

        List<ClienteResumenResponse> clientes = rutina.getClientes()
                .stream()
                .map(this::convertirClienteResumen)
                .toList();

        return new RutinaResponse(
                rutina.getId(),
                rutina.getNombre(),
                rutina.getNivel(),
                clientes
        );
    }

    private ClienteResumenResponse convertirClienteResumen(Cliente cliente) {

        return new ClienteResumenResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getDocumento(),
                cliente.isActivo()
        );
    }
}
