package org.example.hotel_reservation_system.repository.Reservas;

import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservasRepository extends JpaRepository<ReservasEntity, Long> {
}