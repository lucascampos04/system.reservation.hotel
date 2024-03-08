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
                .anyMatch(authority -> "ROLE_CLIENTE_BASICO".equals(authority.getAuthority()) || "ROLE_CLIENTE".equals(authority.getAuthority()))
        ) {
            String emailCliente = authentication.getName();
            ClientesEntity cliente = clientesRepository.findByEmail(emailCliente);

            if (cliente.getRole() == RolesEnum.ROLE_CLIENTE_BASICO || cliente.getRole() == RolesEnum.ROLE_CLIENTE){
                return ResponseEntity.ok().build().hasBody();
            }
        }
        return ResponseEntity.notFound().build().hasBody();

    }

    public boolean AcessAreaVip(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        return false;
    }
}
