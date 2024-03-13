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

    private void applyPartialUpdate(ClientesEntity cliente, Map<String, Object> updates) {
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            if (value != null) {
                switch (fieldName) {
                    case "nome":
                        if (value instanceof String)
                            cliente.setNome((String) value);
                        break;
                    case "email":
                        if (value instanceof String)
                            cliente.setEmail((String) value);
                        break;
                    case "cpf":
                        if (value instanceof String)
                            cliente.setCpf((String) value);
                        break;
                    case "rg":
                        if (value instanceof String)
                            cliente.setRg((String) value);
                        break;
                    default:
                        System.out.println("Campo inválido ou tipo de valor inválido: " + fieldName);
                        break;
                }
            } else {
                System.out.println("Valor nulo para o campo: " + fieldName);
            }
        }
    }


}
