package org.example.hotel_reservation_system.Services.Users;

import org.example.hotel_reservation_system.Dto.Users.UsersDto;
import org.example.hotel_reservation_system.Repository.Users.EmployeeDataRepository;
import org.example.hotel_reservation_system.Repository.Users.UserDataRepository;
import org.example.hotel_reservation_system.Repository.Users.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsersServices {

    private final UsersRepository usersRepository;
    private final EmployeeDataRepository employeeDataRepository;
    private final UserDataRepository userDataRepository;

    public UsersServices(UsersRepository usersRepository, EmployeeDataRepository employeeDataRepository, UserDataRepository userDataRepository) {
        this.usersRepository = usersRepository;
        this.employeeDataRepository = employeeDataRepository;
        this.userDataRepository = userDataRepository;
    }

    public ResponseEntity<String> addUser(UsersDto usersDto) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro proposital: recurso não encontrado.");
    }
}
