package org.example.hotel_reservation_system.controller.Employees;

import org.example.hotel_reservation_system.dto.Employees.EmployeesDto;
import org.example.hotel_reservation_system.services.Employees.get.ListAll.ListAllServices;
import org.example.hotel_reservation_system.services.Employees.post.add.AddEmployeesServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {
    private final AddEmployeesServices addEmployeesServices;
    private final ListAllServices listAllEmployeesServices;
    public EmployeesController(AddEmployeesServices addEmployeesServices, ListAllServices listAllEmployeesServices) {
        this.addEmployeesServices = addEmployeesServices;
        this.listAllEmployeesServices = listAllEmployeesServices;
    }

    @PostMapping("/post/add/employees")
    public ResponseEntity<String> cadastrarCliente(@RequestBody EmployeesDto employeesDto){
        try{
            return addEmployeesServices.addEmployees(employeesDto);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/get/all/employees")
    public ResponseEntity<List<EmployeesDto>> list(){
        List<EmployeesDto> employeesDtos = listAllEmployeesServices.listAllEmployees();
        return ResponseEntity.ok().body(employeesDtos);
    }

}
