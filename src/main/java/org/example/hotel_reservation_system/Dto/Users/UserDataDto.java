package org.example.hotel_reservation_system.Dto.Users;

import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Model.Users.UserData;

@Getter
@Setter
public class UserDataDto {
    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String phone;

    public static UserDataDto fromUserData(UserData userData) {
        UserDataDto dto = new UserDataDto();
        dto.setId(userData.getId());
        dto.setNome(userData.getNome());
        dto.setCpf(userData.getCpf());
        dto.setRg(userData.getRg());
        dto.setEmail(userData.getEmail());
        dto.setPhone(userData.getPhone());
        return dto;
    }
}
