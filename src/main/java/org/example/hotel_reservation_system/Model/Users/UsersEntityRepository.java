package org.example.hotel_reservation_system.Model.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersEntityRepository extends JpaRepository<UsersEntity, Long> {
  }