package org.example.hotel_reservation_system.repository.Plano;

import org.example.hotel_reservation_system.model.Plano.PlanoEntity;
import org.example.hotel_reservation_system.services.Plano.PlanoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<PlanoEntity, Long> {
}
