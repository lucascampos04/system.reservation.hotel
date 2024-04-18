package org.example.hotel_reservation_system.services.patterns.DiscountsReservas;

import org.springframework.stereotype.Service;

@Service
public class LuxuryDiscountStrategy extends DiscountStrategy{
    @Override
    Double aplyDiscount(Double valor) {
        return valor - (valor * 0.15);
    }
}
