package org.example.hotel_reservation_system.services.AddClient;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddClienteService {

    private final ClientesRepository clientesRepository;

    public AddClienteService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public ResponseEntity<String> AdicionarCliente(ClientesDto clientesDto){
        try {
            String messageErroValidationField = verificarCampoExistente(clientesDto);

            if (messageErroValidationField != null){
                return ResponseEntity.badRequest().body("Error : " + messageErroValidationField);
            }

            ClientesEntity clientesEntity = getDads(clientesDto);
            clientesRepository.save(clientesEntity);
            return ResponseEntity.ok("Cliente adicionado com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao inserir cliente");
        }
    }

    private String verificarCampoExistente(ClientesDto clientesDto){
        if (emailExist(clientesDto.getEmail())){
            return "Email já está em uso";
        }

        if (cpfExist(clientesDto.getCpf())){
            return "CPF já está em uso";
        }

        if (rgExist(clientesDto.getRg())){
            return "Rg já está em uso";
        }
        return null;
    }

    private boolean emailExist(String email){
        return clientesRepository.existsByEmail(email);
    }
    private boolean cpfExist(String cpf){
        return clientesRepository.existsByCpf(cpf);
    }

    private boolean rgExist(String rg){
        return clientesRepository.existsByRg(rg);
    }



    private ClientesEntity getDads(ClientesDto clientesDto){
        ClientesEntity clientes = new ClientesEntity();
        clientes.setId(clientes.getId());
        clientes.setNome(clientesDto.getNome());
        clientes.setEmail(clientesDto.getEmail());
        clientes.setCpf(clientesDto.getCpf());
        clientes.setRg(clientesDto.getRg());
        clientes.setEndereco(clientesDto.getEndereco());
        clientes.setCep(clientesDto.getCep());
        clientes.setNumero(clientesDto.getNumero());
        clientes.setEstado(clientesDto.getEstado());
        clientes.setPais(clientesDto.getPais());
        clientes.setStatus(clientesDto.getStatus());
        clientes.setData_registro(LocalDateTime.now());
        clientes.setData_nascimento(clientesDto.getData_nascimento());
        return clientes;
    }






}
