package org.example.hotel_reservation_system.services.Cliente.Patch;

import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

public class PatchServices {

    private final ClientesRepository clientesRepository;
    public PatchServices(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public ResponseEntity<String> patchCliente(Long id, Map<String, Object> updates) {
        try {
            Optional<ClientesEntity> optional = clientesRepository.findById(id);
            if (optional.isPresent()) {
                ClientesEntity cliente = optional.get();
                clientesRepository.save(cliente);
                return ResponseEntity.ok("Cliente atualizado com sucesso");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar cliente");
        }
        return null;
    }

}
