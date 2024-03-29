package org.example.hotel_reservation_system.repository.Reservas;

import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservasRepository extends JpaRepository<ReservasEntity, Long> {
}