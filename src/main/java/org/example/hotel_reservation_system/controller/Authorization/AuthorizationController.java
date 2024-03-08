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
public class AuthorizationController{

    @Autowired
    private AuthorizationsServices authorizationsServices;

    @GetMapping({ "/blocoA", "/blocoB", "/blocoC" })
    public ResponseEntity<String> AcessBlocoA() {
        if (authorizationsServices.AcessSuccessBlockAAndBlockC()) {
            return ResponseEntity.ok().body("Bloco A, Bloco B e Bloco C");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado à Bloco A, Bloco B e Bloco C");
        }
    }

    @GetMapping("/full/acess")
    public ResponseEntity<String> AcessFull() {
        String[] AcessAllBlock = { "/BlocoA", "/BlocoB", "/BlocoC", "/BlocoD", "/BlocoE" };

        if (authorizationsServices.AcessFull(AcessAllBlock)) {
            return ResponseEntity.ok().body("Você tem acesso a todos os blocos");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado a todos os blocos");
        }
    }
}
