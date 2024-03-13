package org.example.hotel_reservation_system.services.Cliente.Patch;

import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Plano.PlanoEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
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
                applyPartialUpdate(cliente, updates);
                clientesRepository.save(cliente);
                return ResponseEntity.ok("Cliente atualizado com sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar cliente");
        }
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
                    case "endereco":
                        if (value instanceof String)
                            cliente.setEndereco((String) value);
                        break;
                    case "cep":
                        if (value instanceof String)
                            cliente.setCep((String) value);
                        break;
                    case "numero":
                        if (value instanceof String)
                            cliente.setNumero((String) value);
                        break;
                    case "estado":
                        if (value instanceof String)
                            cliente.setEstado((String) value);
                        break;
                    case "pais":
                        if (value instanceof String)
                            cliente.setPais((String) value);
                        break;
                    case "data_nascimento":
                        if (value instanceof String)
                            cliente.setData_nascimento((String) value);
                        break;
                    case "status":
                        if (value instanceof StatusEnum)
                            cliente.setStatus((StatusEnum) value);
                        break;
                    case "data_registro":
                        if (value instanceof LocalDateTime)
                            cliente.setData_registro((LocalDateTime) value);
                        break;
                    case "roles":
                        if (value instanceof RolesEnum)
                            cliente.setRole((RolesEnum) value);
                        break;
                    case "plano":
                        if (value instanceof PlanoEntity)
                            cliente.setPlano((PlanoEntity) value);
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
