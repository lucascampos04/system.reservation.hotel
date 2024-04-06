package org.example.hotel_reservation_system.services.DiscountsReservas;

import org.springframework.stereotype.Service;

@Service
public class BasicDiscountStrategy extends DiscountStrategy {
    @Override
    public Double aplyDiscount(Double valor) {
        return valor - (valor * 0.05);
    }
}
