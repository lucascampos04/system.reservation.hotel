package org.example.hotel_reservation_system.services.Cliente.Patch;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatchServices {

    private final ClientesRepository clientesRepository;

    public PatchServices(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public ResponseEntity<String> patchCliente(Long id, ClientesDto updates) {
        try {
            Optional<ClientesEntity> optional = clientesRepository.findById(id);
            if (optional.isPresent()) {
                ClientesEntity cliente = optional.get();
                applyPartialUpdate(cliente, updates);
                clientesRepository.save(cliente);
                return ResponseEntity.ok("Cliente atualizado com sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    private void applyPartialUpdate(ClientesEntity cliente, ClientesDto updates) {
        if (updates.getNome() != null) {
            cliente.setNome(updates.getNome());
        }
        if (updates.getEmail() != null) {
            cliente.setEmail(updates.getEmail());
        }
        if (updates.getCpf() != null) {
            cliente.setCpf(updates.getCpf());
        }
        if (updates.getRg() != null) {
            cliente.setRg(updates.getRg());
        }
        if (updates.getEndereco() != null) {
            cliente.setEndereco(updates.getEndereco());
        }
        if (updates.getCep() != null) {
            cliente.setCep(updates.getCep());
        }
        if (updates.getNumero() != null) {
            cliente.setNumero(updates.getNumero());
        }
        if (updates.getEstado() != null) {
            cliente.setEstado(updates.getEstado());
        }
        if (updates.getPais() != null) {
            cliente.setPais(updates.getPais());
        }
        if (updates.getData_nascimento() != null) {
            cliente.setData_nascimento(updates.getData_nascimento());
        }
        if (updates.getStatus() != null) {
            cliente.setStatus(updates.getStatus());
        }
        if (updates.getData_registro() != null) {
            cliente.setData_registro(updates.getData_registro());
        }
        if (updates.getRole() != null) {
            cliente.setRole(updates.getRole());
        }
    }

}
