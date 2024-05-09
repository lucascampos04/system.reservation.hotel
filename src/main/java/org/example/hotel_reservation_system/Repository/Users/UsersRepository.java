package org.example.hotel_reservation_system.Repository.Users;

import org.example.hotel_reservation_system.Model.Users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
}
