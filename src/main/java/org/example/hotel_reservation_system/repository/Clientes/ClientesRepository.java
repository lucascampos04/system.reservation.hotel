package org.example.hotel_reservation_system.repository.Clientes;

import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesEntity, Long>{

    boolean existsByEmail(String email);
}
