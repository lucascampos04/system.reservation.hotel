package org.example.hotel_reservation_system.test;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MainClient {
    public static void main(String[] args) {
        final String baseUrl = "http://localhost:1234/api/v1/clientes/get/all/clientes";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ClientesDto[]> response = restTemplate.getForEntity(baseUrl, ClientesDto[].class);
        ClientesDto[] clientes = response.getBody();

        for (ClientesDto cliente : clientes) {
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("---------------------------");
        }
    }
}
