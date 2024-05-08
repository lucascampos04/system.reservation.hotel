package org.example.hotel_reservation_system.repository.DadosLogin;

import org.example.hotel_reservation_system.model.DadosLogin.DadosLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosLoginRepository extends JpaRepository<DadosLogin, Long> {
    DadosLogin findByLogin(String email);
}