package org.example.hotel_reservation_system.Dto.Users;

import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Model.Users.EmployeeData;

import java.math.BigDecimal;

@Getter
@Setter
public class EmployeeDataDto {
    private Long id;
    private BigDecimal salary;
    private String position;

    public static EmployeeDataDto fromEmployeeData(EmployeeData employeeData) {
        EmployeeDataDto dto = new EmployeeDataDto();
        dto.setId(employeeData.getId());
        dto.setSalary(employeeData.getSalary());
        dto.setPosition(employeeData.getPosition());
        return dto;
    }
}
