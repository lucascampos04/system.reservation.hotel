package org.example.hotel_reservation_system.services.Cliente.Put;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PutCliente {

    private final ClientesRepository clientesRepository;
    public PutCliente(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public ResponseEntity<String> updateClientes(Long id, ClientesDto clientesDto){
        try {
            Optional<ClientesEntity> optional = clientesRepository.findById(id);
            if (optional.isPresent()){
                String validated = validadFields(clientesDto);

                if (validated != null){
                    return ResponseEntity.badRequest().body("Error : " + validated);
                }

                if (isRgOrCpfAlreadyInUse(clientesDto.getRg(), clientesDto.getCpf(), id)){
                    return ResponseEntity.badRequest().body("O CPF ou o RG já está em uso");
                }
                update(optional.get(), clientesDto);
                return ResponseEntity.ok("Cliente atualizado com sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean isRgOrCpfAlreadyInUse(String rg, String cpf, Long currentClient){
        Optional<ClientesEntity> existingRgClient = clientesRepository.findByRgAndIdNot(rg, currentClient);
        Optional<ClientesEntity> existingCpfClient = clientesRepository.findByCpfAndIdNot(cpf, currentClient);

        return existingRgClient.isPresent() || existingCpfClient.isPresent();
    }
    private String validadFields(ClientesDto clientesDto){
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

        if (!isValidCep(clientesDto.getCep())){
            return "CEP inválido";
        }

        if (!isValidPais(clientesDto.getPais())){
            return "País inválido";
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
        String nameRegex = "[A-Z][a-z].* [A-Z][a-z].*";
        return name != null && name.matches(nameRegex);
    }
    private boolean isValidStatus(String status){
        return status.equals("ATIVO") || status.equals("DESATIVADO");
    }
    private boolean isValidCep(String cep){
        String cepfRegex = "^(?=.*\\d)\\d{1,}$";
        return cep != null && cep.matches(cepfRegex);
    }

    private boolean isValidPais(String pais){
        String paisRegex = "^(?=.*[a-zA-Z])[a-zA-Z]{1,}$";
        return pais != null && pais.matches(paisRegex);
    }
    private void update(ClientesEntity clientes, ClientesDto clientesDto){
        clientes.setNome(clientesDto.getNome());
        clientes.setEmail(clientesDto.getEmail());
        clientes.setCpf(clientesDto.getCpf());
        clientes.setRg(clientesDto.getRg());
        clientes.setEndereco(clientesDto.getEndereco());
        clientes.setCep(clientesDto.getCep());
        clientes.setData_nascimento(clientesDto.getData_nascimento());
        clientes.setNumero(clientesDto.getNumero());
        clientes.setEstado(clientesDto.getEstado());
        clientes.setPais(clientesDto.getPais());
        clientes.setStatus(clientesDto.getStatus());
        clientesRepository.save(clientes);
    }
}
