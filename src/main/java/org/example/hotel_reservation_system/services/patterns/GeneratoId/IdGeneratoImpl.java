package org.example.hotel_reservation_system.services.patterns.GeneratoId;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class IdGeneratoImpl implements IdGenerator{
    @Override
    public long generateId(String text) {
        switch (text){
            case "cliente":
                return generateIdCliente();
            case "reservas":
                return generateIdReservas();
            case "funcionarios":
                return generateIdFuncionarios();
            default:
                throw new IllegalArgumentException("Tipo de entidade desconhecido: " + text);
        }
    }

    private long generateIdCliente(){
        return generateIdInRanger(10000, 99999);
    }

    private long generateIdReservas(){
        return generateIdInRanger(200000, 2999999);
    }

    private Long generateIdFuncionarios(){
        return generateIdInRanger(4000000, 29999999);
    }

    private long generateIdInRanger(int min, int max){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return min + random.nextInt(max - min + 1);
    }
}
