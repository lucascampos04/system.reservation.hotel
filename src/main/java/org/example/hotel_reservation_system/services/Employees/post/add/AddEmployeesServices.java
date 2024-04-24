package org.example.hotel_reservation_system.services.Employees.post.add;

import org.example.hotel_reservation_system.Enum.Cargo.CargoEmployees;
import org.example.hotel_reservation_system.Enum.Contratos.ContratosEmployees;
import org.example.hotel_reservation_system.Enum.Status.StatusEmployees;
import org.example.hotel_reservation_system.dto.Employees.EmployeesDto;
import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;
import org.example.hotel_reservation_system.repository.Employees.EmployeesRepository;
import org.example.hotel_reservation_system.services.EmailServices.Employees.EmployeeSuccessfullyHired;
import org.example.hotel_reservation_system.services.patterns.GeneratoId.IdGenerator;
import org.example.hotel_reservation_system.services.patterns.Regex.MainRegexApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AddEmployeesServices {

    private final EmployeesRepository employeesRepository;
    private final EmployeeSuccessfullyHired employeeSuccessfullyHired;
    private final IdGenerator idGenerator;

    public AddEmployeesServices(EmployeesRepository employeesRepository, EmployeeSuccessfullyHired employeeSuccessfullyHired, IdGenerator idGenerator) {
        this.employeesRepository = employeesRepository;
        this.employeeSuccessfullyHired = employeeSuccessfullyHired;
        this.idGenerator = idGenerator;
    }

    public ResponseEntity<String> addEmployees(EmployeesDto employeesDto) {
        try{
            ResponseEntity<String> messageRegex = realizedValidationsRegex(employeesDto);

            if (messageRegex != null){
                return messageRegex;
            }

            EmployeesEntity employeesEntity = getDads(employeesDto);
            employeesRepository.save(employeesEntity);

            if (employeesEntity != null) {
                employeeSuccessfullyHired.SendEmailEmployeeHired(employeesEntity.getEmail(), employeesEntity.getNome(), employeesEntity.getCargo());
            }

            return ResponseEntity.ok("Funcionaário adicionado com sucesso");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private EmployeesEntity getDads(EmployeesDto employeesDto) {
        EmployeesEntity employeesEntity = new EmployeesEntity();
        Long id = idGenerator.generateId("funcionarios");

        employeesEntity.setId(id);
        employeesEntity.setNome(employeesDto.getNome());
        employeesEntity.setEmail(employeesDto.getEmail());
        employeesEntity.setCpf(employeesDto.getCpf());
        employeesEntity.setRg(employeesDto.getRg());
        employeesEntity.setEndereco(employeesDto.getEndereco());
        employeesEntity.setCep(employeesDto.getCep());
        employeesEntity.setNumero(employeesDto.getNumero());
        employeesEntity.setEstado(employeesDto.getEstado());
        employeesEntity.setPais(employeesDto.getPais());
        employeesEntity.setDataNascimeo(employeesDto.getDataNascimeo());
        employeesEntity.setStatus(StatusEmployees.ATIVO);
        employeesEntity.setCargo(employeesDto.getCargo());
        employeesEntity.setData_registro(LocalDateTime.now());
        employeesEntity.setSalario(employeesDto.getSalario());
        employeesEntity.setContratos(employeesDto.getContratos());
        return employeesEntity;
    }

    private ResponseEntity<String> realizedValidationsRegex (EmployeesDto employeesDto){
        String nameMessage;
        String cpfMessage;
        String rgMessage;
        String cepMessage;
        String paisMessage;

        nameMessage = validarNome(employeesDto.getNome());
        cpfMessage = validarCPF(employeesDto.getCpf());
        rgMessage = validarRG(employeesDto.getRg());
        paisMessage = validarPais(employeesDto.getPais());
        cepMessage = validarCep(employeesDto.getCep());

        if (nameMessage != null && !nameMessage.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro no nome: " + nameMessage);
        }

        if (cpfMessage != null && !cpfMessage.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro no cpf: " + cpfMessage);
        }

        if (rgMessage != null && !rgMessage.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro no rg: " + rgMessage);
        }

        if (paisMessage != null && !paisMessage.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro no pais: " + paisMessage);
        }

        if (cepMessage != null && !cepMessage.isEmpty()) {
            return ResponseEntity.badRequest().body("Erro no cep: " + cepMessage);
        }
        return null;
    }

    private String validarNome(String nome){
        MainRegexApplication.NameRegex nameRegex = new MainRegexApplication.NameRegex();
        return nameRegex.applyRegex(nome);
    }

    private String validarCPF(String cpf){
        MainRegexApplication.CpfRegex cpfRegex = new MainRegexApplication.CpfRegex();
        return cpfRegex.applyRegex(cpf);
    }

    private String validarRG(String rg){
        MainRegexApplication.RgRegex rgRegex = new MainRegexApplication.RgRegex();
        return rgRegex.applyRegex(rg);
    }

    private String validarPais(String pais){
        MainRegexApplication.PaisRegex paisRegex = new MainRegexApplication.PaisRegex();
        return paisRegex.applyRegex(pais);
    }

    private String validarCep(String cep){
        MainRegexApplication.CepRegex cepRegex = new MainRegexApplication.CepRegex();
        return cepRegex.applyRegex(cep);
    }
}
