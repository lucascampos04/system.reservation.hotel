package org.example.hotel_reservation_system.ServiceTest;

import org.example.hotel_reservation_system.dto.Employees.EmployeesDto;
import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;
import org.example.hotel_reservation_system.repository.Employees.EmployeesRepository;
import org.example.hotel_reservation_system.services.Employees.post.add.AddEmployeesServices;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class InsertEmployeesServices {

    @InjectMocks
    private AddEmployeesServices addEmployeesServices;

    @Mock
    private EmployeesRepository employeesRepository;

    public void testAddEmployeesSuccess() {
        EmployeesDto employeesDto = new EmployeesDto();

        when(employeesRepository.save(any(EmployeesEntity.class))).thenReturn(new EmployeesEntity());

        ResponseEntity<String> response = addEmployeesServices.addEmployees(employeesDto);

        assertEquals("Funcionario adicionado com sucesso", response.getBody());

        verify(employeesRepository, times(1)).save(any(EmployeesEntity.class));

       if (response.getBody().equals("Funcionario adicionado com sucesso")){
           System.out.println("Teste funcionou corretamente!");
       } else {
           System.out.println("Teste falhou!");
       }
    }
}
