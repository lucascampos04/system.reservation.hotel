package org.example.hotel_reservation_system.services.Cliente.Post;

import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Plano.PlanoEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.example.hotel_reservation_system.repository.Plano.PlanoRepository;
import org.example.hotel_reservation_system.services.EmailServices.Client.NotificationClientInsert;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import static org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum.valueOf;

@Service
public class AddClienteService {

    private final ClientesRepository clientesRepository;
    private final NotificationClientInsert notificationClientInsert;
    private final PlanoRepository planoRepository;

    public AddClienteService(ClientesRepository clientesRepository, NotificationClientInsert notificationClientInsert, PlanoRepository planoRepository) {
        this.clientesRepository = clientesRepository;
        this.notificationClientInsert = notificationClientInsert;
        this.planoRepository = planoRepository;
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

            notificationClientInsert.SendEmailOfClientCreate(clientesDto.getEmail(), clientesDto.getNome(), clientesDto.getPlano().toString());
            return ResponseEntity.ok("Cliente adicionado com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao inserir cliente");
        }
    }

    private String validarCamposPattern(ClientesDto clientesDto){
        if (clientesDto.getStatus() == null){
            clientesDto.setStatus(StatusEnum.ATIVO);
        }

        if (clientesDto == null){
            return "DTO de cliente é null";
        }

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

        if (!isValidPlano(valueOf(clientesDto.getPlano()))){
            return "Plano inválido";
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
        return status.equals("ATIVO") || status.equals("DESATIVADO") || status.equals("INATIVO");
    }

    private boolean isValidPlano(TipoPlanoEnum planoEnum){
        String[] planos = {
                String.valueOf(TipoPlanoEnum.BASICO),
                String.valueOf(TipoPlanoEnum.PADRAO),
                String.valueOf(TipoPlanoEnum.EXECUTIVO),
                String.valueOf(TipoPlanoEnum.NEGOCIOS),
                String.valueOf(TipoPlanoEnum.LUXO),
                String.valueOf(TipoPlanoEnum.ROMANCE),
                String.valueOf(TipoPlanoEnum.FAMILIA),
                String.valueOf(TipoPlanoEnum.LONGA_ESTADIA),
                String.valueOf(TipoPlanoEnum.VIP),
                String.valueOf(TipoPlanoEnum.COBERTURA),
        };

        for (int i = 0; i < planos.length; i++) {
            if (planos[i].equals(String.valueOf(planoEnum))){
                return true;
            }
        }
        return false;
    }
    private boolean isValidCep(String cep){
        String cepfRegex = "^(?=.*\\d)\\d{1,}$";
        return cep != null && cep.matches(cepfRegex);
    }

    private boolean isValidPais(String pais){
        String paisRegex = "^(?=.*[a-zA-Z])[a-zA-Z]{1,}$";
        return pais != null && pais.matches(paisRegex);
    }
    private String verificarCampoExistente(ClientesDto clientesDto){
        //if (emailExist(clientesDto.getEmail())){
        //    return "Email já está em uso";
        //}

        if (cpfExist(clientesDto.getCpf())){
            return "CPF já está em uso";
        }

        if (rgExist(clientesDto.getRg())){
            return "Rg já está em uso";
        }
        return null;
    }

    //private boolean emailExist(String email){
    //    return clientesRepository.existsByEmail(email);
    //}
    private boolean cpfExist(String cpf){
        return clientesRepository.existsByCpf(cpf);
    }
    private boolean rgExist(String rg){
        return clientesRepository.existsByRg(rg);
    }

    private Long generatedIdUnique(){
        long id = 0;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        do {
            id = 100000 + random.nextInt(900000);
        } while (clientesRepository.existsById(id));
        return id;
    }


    private ClientesEntity getDads(ClientesDto clientesDto){
        ClientesEntity clientes = new ClientesEntity();
        clientes.setId(generatedIdUnique());
        clientes.setNome(clientesDto.getNome());
        clientes.setEmail(clientesDto.getEmail());
        clientes.setCpf(clientesDto.getCpf());
        clientes.setRg(clientesDto.getRg());
        clientes.setEndereco(clientesDto.getEndereco());
        clientes.setCep(clientesDto.getCep());
        clientes.setNumero(clientesDto.getNumero());
        clientes.setEstado(clientesDto.getEstado());
        clientes.setPais(clientesDto.getPais());
        clientes.setStatus(StatusEnum.ATIVO);
        clientes.setData_registro(LocalDateTime.now());
        clientes.setData_nascimento(clientesDto.getData_nascimento());
        clientes.setRole(RolesEnum.ROLE_CLIENTE_BASICO);

        if (clientesDto.getPlano() == null){
            PlanoEntity plano = new PlanoEntity();
            plano.setId(clientes.getId());
            plano.setPlano(TipoPlanoEnum.SEM_PLANO);
            plano.setValor(null);
        }

        if (clientesDto.getPlano() != null) {
            PlanoEntity plano = new PlanoEntity();

            plano.setId(clientes.getId());

            plano.setPlano(valueOf(String.valueOf(clientesDto.getPlano())));

            clientes.setPlano(plano);

            if (plano.getPlano() == TipoPlanoEnum.SEM_PLANO){
                plano.setValor(0.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.PADRAO){
                plano.setValor(100.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.BASICO){
                plano.setValor(50.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.FAMILIA){
                plano.setValor(150.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.LUXO){
                plano.setValor(1500.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.COBERTURA){
                plano.setValor(1000.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.EXECUTIVO){
                plano.setValor(2600.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.NEGOCIOS){
                plano.setValor(5000.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.LONGA_ESTADIA){
                plano.setValor(3000.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.VIP){
                plano.setValor(4000.0);
            }

            if (plano.getPlano() == TipoPlanoEnum.ROMANCE){
                plano.setValor(3500.0);
            }
            planoRepository.save(plano);
        }
        return clientes;
    }
}