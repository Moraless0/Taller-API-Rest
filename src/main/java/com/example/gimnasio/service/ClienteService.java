package com.example.gimnasio.service;

import com.example.gimnasio.dto.request.ClienteRequest;
import com.example.gimnasio.dto.response.ClienteResponse;
import com.example.gimnasio.dto.response.RutinaResumenResponse;
import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.entity.Rutina;
import com.example.gimnasio.repository.ClienteRepository;
import com.example.gimnasio.repository.RutinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final RutinaRepository rutinaRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          RutinaRepository rutinaRepository) {
        this.clienteRepository = clienteRepository;
        this.rutinaRepository = rutinaRepository;
    }

    public ClienteResponse crearCliente(ClienteRequest request) {

        if (clienteRepository.existsByDocumento(request.getDocumento())) {
            throw new RuntimeException("Ya existe un cliente con ese documento");
        }

        Cliente cliente = new Cliente(
                request.getNombre(),
                request.getDocumento(),
                request.getActivo()
        );

        Cliente guardado = clienteRepository.save(cliente);

        return convertirCliente(guardado);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listarClientes() {

        return clienteRepository.findByActivoTrue()
                .stream()
                .map(this::convertirCliente)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarCliente(Long id) {

        Cliente cliente = obtenerCliente(id);

        return convertirCliente(cliente);
    }

    public ClienteResponse actualizarCliente(Long id, ClienteRequest request) {

        Cliente cliente = obtenerCliente(id);

        if (clienteRepository.existsByDocumentoAndIdNot(request.getDocumento(), id)) {
            throw new RuntimeException("Ya existe un cliente con ese documento");
        }

        cliente.setNombre(request.getNombre());
        cliente.setDocumento(request.getDocumento());
        cliente.setActivo(request.getActivo());

        Cliente actualizado = clienteRepository.save(cliente);

        return convertirCliente(actualizado);
    }

    public void eliminarCliente(Long id) {

        Cliente cliente = obtenerCliente(id);

        cliente.setActivo(false);
        clienteRepository.save(cliente);
    }

    public ClienteResponse asignarRutina(Long clienteId, Long rutinaId) {

        Cliente cliente = obtenerCliente(clienteId);
        Rutina rutina = obtenerRutina(rutinaId);

        cliente.asignarRutina(rutina);

        Cliente guardado = clienteRepository.save(cliente);

        return convertirCliente(guardado);
    }

    @Transactional(readOnly = true)
    public List<RutinaResumenResponse> listarRutinasDeCliente(Long clienteId) {

        Cliente cliente = obtenerCliente(clienteId);

        return cliente.getRutinas()
                .stream()
                .map(this::convertirRutinaResumen)
                .toList();
    }

    public void quitarRutina(Long clienteId, Long rutinaId) {

        Cliente cliente = obtenerCliente(clienteId);
        Rutina rutina = obtenerRutina(rutinaId);

        if (!cliente.getRutinas().contains(rutina)) {
            throw new RuntimeException("La rutina no está asignada al cliente");
        }

        cliente.quitarRutina(rutina);
        clienteRepository.save(cliente);
    }

    private Cliente obtenerCliente(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cliente no encontrado")
                );
    }

    private Rutina obtenerRutina(Long id) {

        return rutinaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rutina no encontrada")
                );
    }

    private ClienteResponse convertirCliente(Cliente cliente) {

        List<RutinaResumenResponse> rutinas = cliente.getRutinas()
                .stream()
                .map(this::convertirRutinaResumen)
                .toList();

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getDocumento(),
                cliente.isActivo(),
                rutinas
        );
    }

    private RutinaResumenResponse convertirRutinaResumen(Rutina rutina) {

        return new RutinaResumenResponse(
                rutina.getId(),
                rutina.getNombre(),
                rutina.getNivel()
        );
    }
}
