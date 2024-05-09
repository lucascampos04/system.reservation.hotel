package org.example.hotel_reservation_system.Dto.Users;

import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Model.Users.EmployeeData;
import org.example.hotel_reservation_system.Model.Users.UserData;
import org.example.hotel_reservation_system.Model.Users.UsersEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class UsersDto implements Serializable {
    private Long id;
    private LocalDateTime createdAt;
    private String typeUser;

    private UserDataDto userData;
    private EmployeeDataDto employeeData;

    public static UsersDto fromEntity(UsersEntity entity) {
        UsersDto dto = new UsersDto();
        dto.setId(entity.getId());
        dto.setTypeUser(entity.getTypeUser());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getUserData() != null) {
            dto.setUserData(UserDataDto.fromUserData(entity.getUserData()));
        }

        if (entity.getEmployeeData() != null) {
            dto.setEmployeeData(EmployeeDataDto.fromEmployeeData(entity.getEmployeeData()));
        }

        return dto;
    }
}
