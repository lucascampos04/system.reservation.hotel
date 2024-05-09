package org.example.hotel_reservation_system.Controller;

import lombok.Getter;
import org.example.hotel_reservation_system.Dto.Users.UsersDto;
import org.example.hotel_reservation_system.Services.Users.UsersServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v2/users")
public class UsersController {

    private final UsersServices usersServices;

    public UsersController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @PostMapping("/add/users")
    public ResponseEntity<String> addUsers(
            @RequestBody UsersDto usersDto
    ){
        ResponseEntity<String> response = usersServices.addUser(usersDto);
        return response;
    }
}
