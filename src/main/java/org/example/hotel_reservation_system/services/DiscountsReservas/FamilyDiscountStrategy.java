package org.example.hotel_reservation_system.services.DiscountsReservas;

import org.springframework.stereotype.Service;

@Service
public class FamilyDiscountStrategy extends DiscountStrategy{
    @Override
    Double aplyDiscount(Double valor) {
        return valor - (valor * 0.1);
    }
}
