package org.example.hotel_reservation_system.repository.Clientes;

import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesEntity, Long>{

    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    Optional<ClientesEntity> findByRgAndIdNot(String rg, Long id);
    Optional<ClientesEntity> findByCpfAndIdNot(String cpf, Long id);

    ClientesEntity findByEmail(String email);

    boolean existsByRg(String rgUpdate);
}
