package org.example.hotel_reservation_system.services.Employees.post.add;

import org.example.hotel_reservation_system.Enum.Cargo.CargoEmployees;
import org.example.hotel_reservation_system.Enum.Contratos.ContratosEmployees;
import org.example.hotel_reservation_system.Enum.Status.StatusEmployees;
import org.example.hotel_reservation_system.dto.Employees.EmployeesDto;
import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;
import org.example.hotel_reservation_system.repository.Employees.EmployeesRepository;
import org.example.hotel_reservation_system.services.EmailServices.Employees.EmployeeSuccessfullyHired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AddEmployeesServices {

    private final EmployeesRepository employeesRepository;
    private final EmployeeSuccessfullyHired employeeSuccessfullyHired;
    public AddEmployeesServices(EmployeesRepository employeesRepository, EmployeeSuccessfullyHired employeeSuccessfullyHired) {
        this.employeesRepository = employeesRepository;
        this.employeeSuccessfullyHired = employeeSuccessfullyHired;
    }

    public ResponseEntity<String> addEmployees(EmployeesDto employeesDto) {
        try{
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

        employeesEntity.setId(genertatorId());
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

    private Long genertatorId() {
        long id = 0;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        do {
            id = 100000000 + random.nextLong(999999999);
        } while (employeesRepository.existsById(id));
        return id;
    }
}
