package org.example.hotel_reservation_system.Repository.Users;

import org.example.hotel_reservation_system.Model.Users.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDataRepository extends JpaRepository<EmployeeData, Long> {
}