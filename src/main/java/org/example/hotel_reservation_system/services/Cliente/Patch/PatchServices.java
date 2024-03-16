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
        StringBuilder change = new StringBuilder("Alterações realizadas: \n");

        String oldNome = cliente.getNome();
        String oldEmail = cliente.getEmail();
        String oldCpf = cliente.getCpf();
        String oldRg = cliente.getRg();
        String oldEndereco = cliente.getEndereco();
        String oldCep = cliente.getCep();
        String oldNumero = cliente.getNumero();
        String oldEstado = cliente.getEstado();
        String oldPais = cliente.getPais();
        String oldData_nascimento = cliente.getData_nascimento();
        String oldStatus = String.valueOf(cliente.getStatus());
        String oldRole = String.valueOf(cliente.getRole());

        if (updates.getNome() != null) {
            change.append("Nome: ").append(oldNome).append(" -> ").append(updates.getNome()).append("\n");
            cliente.setNome(updates.getNome());
        }
        if (updates.getEmail() != null) {
            change.append("Email: ").append(oldEmail).append(" -> ").append(updates.getEmail()).append("\n");
            cliente.setEmail(updates.getEmail());
        }
        if (updates.getCpf() != null) {
            change.append("CPF: ").append(oldCpf).append(" -> ").append(updates.getCpf()).append("\n");
            cliente.setCpf(updates.getCpf());
        }
        if (updates.getRg() != null) {
            change.append("RG: ").append(oldRg).append(" -> ").append(updates.getRg()).append("\n");
            cliente.setRg(updates.getRg());
        }
        if (updates.getEndereco() != null) {
            change.append("Endereço: ").append(oldEndereco).append(" -> ").append(updates.getEndereco()).append("\n");
            cliente.setEndereco(updates.getEndereco());
        }
        if (updates.getCep() != null) {
            change.append("CEP: ").append(oldCep).append(" -> ").append(updates.getCep()).append("\n");
            cliente.setCep(updates.getCep());
        }
        if (updates.getNumero() != null) {
            change.append("Número: ").append(oldNumero).append(" -> ").append(updates.getNumero()).append("\n");
            cliente.setNumero(updates.getNumero());
        }
        if (updates.getEstado() != null) {
            change.append("Estado: ").append(oldEstado).append(" -> ").append(updates.getEstado()).append("\n");
            cliente.setEstado(updates.getEstado());
        }
        if (updates.getPais() != null) {
            change.append("País: ").append(oldPais).append(" -> ").append(updates.getPais()).append("\n");
            cliente.setPais(updates.getPais());
        }
        if (updates.getData_nascimento() != null) {
            change.append("Data de nascimento: ").append(oldData_nascimento).append(" -> ").append(updates.getData_nascimento()).append("\n");
            cliente.setData_nascimento(updates.getData_nascimento());
        }
        if (updates.getStatus() != null) {
            change.append("Status: ").append(oldStatus).append(" -> ").append(updates.getStatus()).append("\n");
            cliente.setStatus(updates.getStatus());
        }
        if (updates.getRole() != null) {
            change.append("Role: ").append(oldRole).append(" -> ").append(updates.getRole()).append("\n");
            cliente.setRole(updates.getRole());
        }

        if (!change.toString().equals("Alterações realizadas: \n")){
            System.out.println(change.toString());
        }
    }

}
