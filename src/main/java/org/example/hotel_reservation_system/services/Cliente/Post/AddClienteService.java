package org.example.hotel_reservation_system.services.Cliente.Post;

import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.DadosLogin.DadosLogin;
import org.example.hotel_reservation_system.model.Plano.PlanoEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.example.hotel_reservation_system.repository.DadosLogin.DadosLoginRepository;
import org.example.hotel_reservation_system.repository.Plano.PlanoRepository;
import org.example.hotel_reservation_system.services.EmailServices.Client.NotificationClientInsert;
import org.example.hotel_reservation_system.services.patterns.FieldsExisting.MainFieldExisting;
import org.example.hotel_reservation_system.services.patterns.GeneratoId.IdGeneratoImpl;
import org.example.hotel_reservation_system.services.patterns.PlansValues.MainAplicationValues;
import org.example.hotel_reservation_system.services.patterns.Regex.MainRegexApplication;
import org.example.hotel_reservation_system.services.patterns.RegisterAccount.EncriptandoPassword;
import org.example.hotel_reservation_system.services.patterns.RegisterAccount.TypeAcessOfCreateAccount;
import org.example.hotel_reservation_system.services.patterns.RegisterAccount.VerifyLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum.valueOf;

@Service
public class AddClienteService {

    private final ClientesRepository clientesRepository;
    private final NotificationClientInsert notificationClientInsert;
    private final PlanoRepository planoRepository;
    private final IdGeneratoImpl idGenerato;
    private final DadosLoginRepository dadosLoginRepository;
    private final TypeAcessOfCreateAccount typeAcessOfCreateAccount;
    private final EncriptandoPassword encriptandoPassword;
    private final VerifyLogin verifyLogin;

    public AddClienteService(ClientesRepository clientesRepository,
                             NotificationClientInsert notificationClientInsert,
                             PlanoRepository planoRepository,
                             IdGeneratoImpl idGenerato,
                             DadosLoginRepository dadosLoginRepository,
                             TypeAcessOfCreateAccount typeAcessOfCreateAccount,
                             EncriptandoPassword encriptandoPassword,
                             VerifyLogin verifyLogin) {
        this.clientesRepository = clientesRepository;
        this.notificationClientInsert = notificationClientInsert;
        this.planoRepository = planoRepository;
        this.idGenerato = idGenerato;
        this.dadosLoginRepository = dadosLoginRepository;
        this.typeAcessOfCreateAccount = typeAcessOfCreateAccount;
        this.encriptandoPassword = encriptandoPassword;
        this.verifyLogin = verifyLogin;
    }

    @SuppressWarnings("null")
    public ResponseEntity<String> AdicionarCliente(ClientesDto clientesDto){
        try {
            String messageErroValidationField = verificarCampoExistente(clientesDto);
            ResponseEntity<String> messageErroValidationRegex = realizedValidationsRegex(clientesDto);
            if (messageErroValidationRegex != null){
                return messageErroValidationRegex;
            }
            if (messageErroValidationField != null){
                return ResponseEntity.badRequest().body("Error : " + messageErroValidationField);
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
    private String verificarCampoExistente(ClientesDto clientesDto){
        String cpf = clientesDto.getCpf();
        String rg = clientesDto.getRg();

        String cpfValidationMessage = buscarCpf(cpf);
        String rgValidationMessage = buscarRg(rg);

        if (cpfValidationMessage != null || rgValidationMessage != null){
            return cpfValidationMessage != null ? cpfValidationMessage : rgValidationMessage;
        }
        return null;
    }
    private String buscarCpf(String cpf){
        MainFieldExisting.CpfExisting cpfExisting = new MainFieldExisting.CpfExisting();

        String cpfUpdate = cpfExisting.appliPattern(cpf);

        if (clientesRepository.existsByCpf(cpfUpdate)){
            return "CPF já está em uso";
        }
        return null;
    }
    private String buscarRg(String rg){
        MainFieldExisting.RgExisting rgExisting = new MainFieldExisting.RgExisting();

        String rgUpdate = rgExisting.appliPattern(rg);

        if (clientesRepository.existsByRg(rgUpdate)){
            return "O Rg já está em uso";
        }
        return null;
    }
    private ClientesEntity getDads(ClientesDto clientesDto){
        ClientesEntity clientes = new ClientesEntity();

        clientes.setId(idGenerato.generateId("cliente"));
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
            TipoPlanoEnum planoEnum = valueOf(clientesDto.getPlano());
            plano.setPlano(planoEnum);
            clientes.setPlano(plano);

            MainAplicationValues.FlatValue aplicarValor = new MainAplicationValues.FlatValue();
            double valorPlano = aplicarValor.applyValues(planoEnum, 0.0);

            plano.setValor(valorPlano);

            planoRepository.save(plano);
        }

        DadosLogin dadosLogin = new DadosLogin();
        dadosLogin.setIdCliente(clientes.getId());
        dadosLogin.setId(idGenerato.generateId("cliente"));

        dadosLogin.setLogin(clientesDto.getLogin());

        String typeLogin = verifyLogin.verifyLogin(clientesDto.getLogin());
        dadosLogin.setTypeLogin(typeLogin);

        String convertedId = clientes.getId().toString();
        dadosLogin.setTypeAcess(typeAcessOfCreateAccount.getType(convertedId));

        String password = clientesDto.getPassword();

        dadosLogin.setPassword(encriptandoPassword.encriptarPassword(password));
        dadosLoginRepository.save(dadosLogin);
        System.out.println("Aplicando login");

        return clientes;
    }

    private ResponseEntity<String> realizedValidationsRegex(ClientesDto clientesDto){
        String nomeValidationMessage = validarNome(clientesDto.getNome());
        String cpfValidationMessage = validarCpf(clientesDto.getCpf());
        String cepValidationMessage = validarCep(clientesDto.getCep());
        String paisValidationMessage = paisRegex(clientesDto.getPais());
        String rgValidationMessage = rgRegex(clientesDto.getRg());

        if (nomeValidationMessage != null){
            return ResponseEntity.badRequest().body("Erro no nome: " + nomeValidationMessage);
        }

        if (cpfValidationMessage != null){
            return ResponseEntity.badRequest().body("Erro no cpf: " + cpfValidationMessage);
        }

        if (cepValidationMessage != null){
            return ResponseEntity.badRequest().body("Erro no cep: " + cepValidationMessage);
        }

        if (paisValidationMessage != null){
            return ResponseEntity.badRequest().body("Erro no país: " + paisValidationMessage);
        }

        if (rgValidationMessage != null){
            return ResponseEntity.badRequest().body("Erro no rg: " + paisValidationMessage);
        }
        return null;
    }
    private String validarNome(String nome){
        MainRegexApplication.NameRegex nameRegex = new MainRegexApplication.NameRegex();
        return nameRegex.applyRegex(nome);
    }
    private String validarCpf(String cpf){
        MainRegexApplication.CpfRegex cpfRegex = new MainRegexApplication.CpfRegex();
        return cpfRegex.applyRegex(cpf);
    }
    private String validarCep(String cep){
        MainRegexApplication.CepRegex rgRegex = new MainRegexApplication.CepRegex();
        return rgRegex.applyRegex(cep);
    }
    private String paisRegex(String pais){
        MainRegexApplication.PaisRegex paisRegex = new MainRegexApplication.PaisRegex();
        return paisRegex.applyRegex(pais);
    }
    private String rgRegex(String rg){
        MainRegexApplication.RgRegex rgRegex = new MainRegexApplication.RgRegex();
        return rgRegex.applyRegex(rg);
    }
}