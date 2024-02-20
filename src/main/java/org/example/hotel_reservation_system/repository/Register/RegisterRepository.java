package org.example.hotel_reservation_system.repository.Register;

import org.example.hotel_reservation_system.model.Register.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long>{
    
}
