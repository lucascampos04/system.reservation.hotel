package org.example.hotel_reservation_system.services.Cliente.Patch;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.example.hotel_reservation_system.services.EmailServices.Client.NotifactionUpdateClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatchServices {

    private final ClientesRepository clientesRepository;
    private final NotifactionUpdateClient notifactionUpdateClient;

    public PatchServices(ClientesRepository clientesRepository, NotifactionUpdateClient notifactionUpdateClient) {
        this.clientesRepository = clientesRepository;
        this.notifactionUpdateClient = notifactionUpdateClient;
    }

    public ResponseEntity<String> patchCliente(Long id, ClientesDto updates) {
        try {
            Optional<ClientesEntity> optional = clientesRepository.findById(id);
            if (optional.isPresent()) {
                ClientesEntity cliente = optional.get();
                List<String> changeMessages = applyPartialUpdate(cliente, updates);
                clientesRepository.save(cliente);

                if (!changeMessages.isEmpty()) {
                    notifactionUpdateClient.SendEmailUpdateClient(cliente.getEmail(), cliente.getNome(), changeMessages.toString());
                }

                return ResponseEntity.ok("Cliente atualizado com sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    private List<String> applyPartialUpdate(ClientesEntity cliente, ClientesDto updates) {
        List<String> changeMessages = new ArrayList<>();

        if (updates.getNome() != null) {
            changeMessages.add("Nome: " + cliente.getNome() + " -> " + updates.getNome());
            cliente.setNome(updates.getNome());
        }
        if (updates.getEmail() != null) {
            changeMessages.add("Email: " + cliente.getEmail() + " -> " + updates.getEmail());
            cliente.setEmail(updates.getEmail());
        }
        if (updates.getCpf() != null) {
            changeMessages.add("CPF: " + cliente.getCpf() + " -> " + updates.getCpf());
            cliente.setCpf(updates.getCpf());
        }
        if (updates.getRg() != null) {
            changeMessages.add("RG: " + cliente.getRg() + " -> " + updates.getRg());
            cliente.setRg(updates.getRg());
        }
        if (updates.getEndereco() != null) {
            changeMessages.add("Endereço: " + cliente.getEndereco() + " -> " + updates.getEndereco());
            cliente.setEndereco(updates.getEndereco());
        }
        if (updates.getCep() != null) {
            changeMessages.add("CEP: " + cliente.getCep() + " -> " + updates.getCep());
            cliente.setCep(updates.getCep());
        }
        if (updates.getNumero() != null) {
            changeMessages.add("Número: " + cliente.getNumero() + " -> " + updates.getNumero());
            cliente.setNumero(updates.getNumero());
        }
        if (updates.getEstado() != null) {
            changeMessages.add("Estado: " + cliente.getEstado() + " -> " + updates.getEstado());
            cliente.setEstado(updates.getEstado());
        }
        if (updates.getPais() != null) {
            changeMessages.add("País: " + cliente.getPais() + " -> " + updates.getPais());
            cliente.setPais(updates.getPais());
        }
        if (updates.getData_nascimento() != null) {
            changeMessages.add("Data de nascimento: " + cliente.getData_nascimento() + " -> " + updates.getData_nascimento());
            cliente.setData_nascimento(updates.getData_nascimento());
        }
        if (updates.getStatus() != null) {
            changeMessages.add("Status: " + cliente.getStatus() + " -> " + updates.getStatus());
            cliente.setStatus(updates.getStatus());
        }
        if (updates.getRole() != null) {
            changeMessages.add("Role: " + cliente.getRole() + " -> " + updates.getRole());
            cliente.setRole(updates.getRole());
        }

        return changeMessages;
    }
}
