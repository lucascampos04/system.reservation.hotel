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

    @SuppressWarnings("null")
    public ResponseEntity<String> AdicionarCliente(ClientesDto clientesDto){
        try {
            String messageErroValidationField = verificarCampoExistente(clientesDto);
            String validarCampos = validarCamposPattern(clientesDto);

            if (messageErroValidationField != null){
                return ResponseEntity.badRequest().body("Error : " + messageErroValidationField);
            }

            if (validarCampos != null){
                return ResponseEntity.badRequest().body("Error : " + validarCampos);
            }

            ClientesEntity clientesEntity = getDads(clientesDto);
            clientesRepository.save(clientesEntity);
            return ResponseEntity.ok("Cliente adicionado com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao inserir cliente");
        }
    }

    private String validarCamposPattern(ClientesDto clientesDto){
        if (containsWhitespace(clientesDto.getEmail())) {
            return "O Email não pode conter espaços";
        }

        if (!isValidCpf(clientesDto.getCpf())) {
            return "CPF inválido";
        }

        if (!isValidRg(clientesDto.getRg())) {
            return "RG inválido";
        }

        if (!isValidName(clientesDto.getNome())) {
            return "Nome inválido";
        }

        if (!isValidStatus(clientesDto.getStatus().toString())) {
            return "Status inválido";
        }
        return null;
    }

    private boolean containsWhitespace(String value) {
        return value != null && value.contains(" ");
    }
    private boolean isValidCpf(String cpf){
        String cpfRegex = "^\\d{11}$";
        return cpf != null && cpf.matches(cpfRegex);
    }
    private boolean isValidRg(String rg){
        String rgRegex = "^[0-9]{1,2}.?[0-9]{3}.?[0-9]{3}-?[0-9Xx]$";
        return rg != null && rg.matches(rgRegex);
    }
    private boolean isValidName(String name){
        String nameRegex = "^[a-zA-Z]";
        return name!= null && name.matches(nameRegex);
    }
    private boolean isValidStatus(String status){
        if (status.equals("ATIVO") || status.equals("DESATIVADO")){
            return true;
        }
        return false;
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