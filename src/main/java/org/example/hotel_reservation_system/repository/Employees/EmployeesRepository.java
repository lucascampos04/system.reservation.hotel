package org.example.hotel_reservation_system.repository.Employees;

import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository<EmployeesEntity, Long>{
    
}
