package org.example.hotel_reservation_system.Enum.Planos;

import lombok.Getter;

@Getter
public enum TipoPlanoEnum {
    SEM_PLANO("Sem plano"),
    BASICO("Plano Básico"),
    PADRAO("Plano Padrão"),
    EXECUTIVO("Plano Executivo"),
    NEGOCIOS("Plano de Negócios"),
    LUXO("Plano de Luxo"),
    ROMANCE("Pacote de Romance"),
    FAMILIA("Pacote Família"),
    LONGA_ESTADIA("Plano de Longa Estadia"),
    VIP("Pacote VIP"),
    COBERTURA("Plano de Cobertura");

    private final String descricao;

    TipoPlanoEnum(String descricao) {
        this.descricao = descricao;
    }

}
