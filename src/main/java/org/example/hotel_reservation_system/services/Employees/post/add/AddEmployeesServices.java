package org.example.hotel_reservation_system.services.Employees.post.add;

import org.example.hotel_reservation_system.dto.Employees.EmployeesDto;
import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;
import org.example.hotel_reservation_system.repository.Employees.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddEmployeesServices {
    @Autowired
    private EmployeesRepository employeesRepository;

    public ResponseEntity<String> addEmployees(EmployeesDto employeesDto){
        try {
            EmployeesEntity entity = getDads(employeesDto);
            employeesRepository.save(entity);

            return ResponseEntity.ok().body("Funcionario adicionado com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private EmployeesEntity getDads(EmployeesDto dto){
        EmployeesEntity employeesEntity = new EmployeesEntity();
        employeesEntity.setId(dto.getId());
        employeesEntity.setNome(dto.getNome());
        employeesEntity.setEmail(dto.getEmail());
        employeesEntity.setCpf(dto.getCpf());
        employeesEntity.setRg(dto.getRg());
        employeesEntity.setDataNascimeo(dto.getDataNascimeo());
        employeesEntity.setStatus(dto.getStatus());
        employeesEntity.setCargo(dto.getCargo());
        employeesEntity.setData_registro(dto.getData_registro());
        employeesEntity.setSalario(dto.getSalario());
        employeesEntity.setCep(dto.getCep());
        employeesEntity.setNumero(dto.getNumero());
        employeesEntity.setEstado(dto.getEstado());
        employeesEntity.setPais(dto.getPais());
        return employeesEntity;
    }
}
