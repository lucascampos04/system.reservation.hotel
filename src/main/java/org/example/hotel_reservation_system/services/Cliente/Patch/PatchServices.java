package org.example.hotel_reservation_system.services.Cliente.Patch;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.example.hotel_reservation_system.services.EmailServices.Client.NotifactionUpdateClient;
import org.example.hotel_reservation_system.services.patterns.UpdateColumns.Columns.PatchMainPattern;
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

        PatchMainPattern.NamePatch namePatch = new PatchMainPattern.NamePatch();
        PatchMainPattern.EmailPatch emailPatch = new PatchMainPattern.EmailPatch();
        PatchMainPattern.CpfPatch cpfPatch = new PatchMainPattern.CpfPatch();
        PatchMainPattern.RgPatch rgPatch = new PatchMainPattern.RgPatch();
        PatchMainPattern.EnderecoPatch enderecoPatch = new PatchMainPattern.EnderecoPatch();
        PatchMainPattern.CepPatch cepPatch = new PatchMainPattern.CepPatch();
        PatchMainPattern.NumeroPatch numeroPatch = new PatchMainPattern.NumeroPatch();
        PatchMainPattern.EstadoPatch estadoPatch = new PatchMainPattern.EstadoPatch();
        PatchMainPattern.PaisPatch paisPatch = new PatchMainPattern.PaisPatch();
        PatchMainPattern.DataNascimentoPatch dataNascimentoPatch = new PatchMainPattern.DataNascimentoPatch();
        PatchMainPattern.StatusPatch statusPatch = new PatchMainPattern.StatusPatch();
        PatchMainPattern.RolePatch rolePatch = new PatchMainPattern.RolePatch();

        if (updates.getNome() != null) {
            String changeMessage = namePatch.updateColumn(cliente.getNome(), updates.getNome());
            changeMessages.add(changeMessage);
            cliente.setNome(updates.getNome());
        }
        if (updates.getEmail() != null) {
            String changeMessage = emailPatch.updateColumn(cliente.getEmail(), updates.getEmail());
            changeMessages.add(changeMessage);
            cliente.setEmail(updates.getEmail());
        }
        if (updates.getCpf() != null) {
            String changeMessage = cpfPatch.updateColumn(cliente.getCpf(), updates.getCpf());
            changeMessages.add(changeMessage);
            cliente.setCpf(updates.getCpf());
        }
        if (updates.getRg() != null) {
            String changeMessage = rgPatch.updateColumn(cliente.getRg(), updates.getRg());
            changeMessages.add(changeMessage);
            cliente.setRg(updates.getRg());
        }
        if (updates.getEndereco() != null) {
            String changeMessage = enderecoPatch.updateColumn(cliente.getEndereco(), updates.getEndereco());
            changeMessages.add(changeMessage);
            cliente.setEndereco(updates.getEndereco());
        }
        if (updates.getCep() != null) {
            String changeMessage = cepPatch.updateColumn(cliente.getCep(), updates.getCep());
            changeMessages.add(changeMessage);
            cliente.setCep(updates.getCep());
        }
        if (updates.getNumero() != null) {
            String changeMessage = numeroPatch.updateColumn(cliente.getNumero(), updates.getNumero());
            changeMessages.add(changeMessage);
            cliente.setNumero(updates.getNumero());
        }
        if (updates.getEstado() != null) {
            String changeMessage = estadoPatch.updateColumn(cliente.getEstado(), updates.getEstado());
            changeMessages.add(changeMessage);
            cliente.setEstado(updates.getEstado());
        }
        if (updates.getPais() != null) {
            String changeMessage = paisPatch.updateColumn(cliente.getPais(), updates.getPais());
            changeMessages.add(changeMessage);
            cliente.setPais(updates.getPais());
        }
        if (updates.getData_nascimento() != null) {
            String changeMessage = dataNascimentoPatch.updateColumn(cliente.getData_nascimento(), updates.getData_nascimento());
            changeMessages.add(changeMessage);
            cliente.setData_nascimento(updates.getData_nascimento());
        }
        if (updates.getStatus() != null) {
            String changeMessage = statusPatch.getColumnType(cliente.getStatus(),updates.getStatus());
            changeMessages.add(changeMessage);
            cliente.setStatus(updates.getStatus());
        }
        if (updates.getRole() != null) {
            String changeMessage = rolePatch.getColumnType(cliente.getRole(), updates.getRole());
            changeMessages.add(changeMessage);
            cliente.setRole(updates.getRole());
        }

        return changeMessages;
    }
}
