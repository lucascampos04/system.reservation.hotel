package org.example.hotel_reservation_system;

import org.example.hotel_reservation_system.test.GeneratorJson.GeneratorJson;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelReservationSystemApplication{

    public static void main(String[] args) {
        SpringApplication.run(HotelReservationSystemApplication.class, args);
        System.out.println("Router Default Client : http://localhost:1234/api/v1/clientes/ ");
        System.out.println("Router Default Employees : http://localhost:1234/api/v1/employees/ ");
        System.out.println("Router Default Register : http://localhost:1234/api/v1/register/ ");

        System.out.println(GeneratorJson.main());
    }

}
