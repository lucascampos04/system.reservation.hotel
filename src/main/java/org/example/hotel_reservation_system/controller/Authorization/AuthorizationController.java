package org.example.hotel_reservation_system.controller.Authorization;

import org.example.hotel_reservation_system.services.Authorizations.AuthorizationsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController {
    @Autowired
    private AuthorizationsServices authorizationsServices;
    @GetMapping({"/blocoA", "/blocoC", "/blocoC"})
    public ResponseEntity<String> AcessBlocoA() {
        if (authorizationsServices.AcessSuccessBlockAAndBlockC()){
            return ResponseEntity.ok().body("Bloco A, Bloco B e Bloco C");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado à Bloco A, Bloco B e Bloco C");
        }
    }
}
