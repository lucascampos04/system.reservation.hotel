package org.example.hotel_reservation_system.services.Employees.get.ListAll;

import org.example.hotel_reservation_system.dto.Employees.EmployeesDto;
import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;
import org.example.hotel_reservation_system.repository.Employees.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllServices {
    @Autowired
    private EmployeesRepository employeesRepository;
    public List<EmployeesDto> listAllEmployees(){
        try {
            List<EmployeesEntity> employees = employeesRepository.findAll();
            return employees.stream().map(EmployeesDto::fromEntity)
                  .collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
