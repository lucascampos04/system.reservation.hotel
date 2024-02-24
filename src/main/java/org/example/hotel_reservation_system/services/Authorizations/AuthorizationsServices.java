package org.example.hotel_reservation_system.services.Authorizations;

import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationsServices {
    @Autowired
    private ClientesRepository clientesRepository;
    public boolean AcessSuccessBlockAAndBlockC(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_CLIENTE"))
        ) {
            String emailCliente = authentication.getName();
            ClientesEntity cliente = clientesRepository.findByEmail(emailCliente);

            if (cliente != null && cliente.getRole() == RolesEnum.ROLE_CLIENTE){
                return ResponseEntity.ok().build().hasBody();
            }
        }
        return ResponseEntity.notFound().build().hasBody();
    }
}
