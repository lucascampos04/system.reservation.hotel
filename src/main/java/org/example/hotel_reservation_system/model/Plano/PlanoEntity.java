package org.example.hotel_reservation_system.model.Plano;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;

@Getter
@Setter
@Entity
public class PlanoEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "plano")
    private TipoPlanoEnum plano;

    @Column(name = "valor")
    private Double valor;

    @OneToOne(mappedBy = "plano")
    private ClientesEntity cliente;
    public PlanoEntity(Long id,
                       TipoPlanoEnum plano,
                       Double valor,
                       ClientesEntity cliente) {
        Id = id;
        this.plano = plano;
        this.valor = (valor != null) ? valor : getDefaultValor(plano);
        this.cliente = cliente;
    }
    public PlanoEntity(){

    }

    private Double getDefaultValor(TipoPlanoEnum tipoPlano) {
        switch (tipoPlano) {
            case SEM_PLANO:
                return 0.0;
            case BASICO:
                return 80.00;
            case PADRAO:
                return 120.00;
            case EXECUTIVO:
                return 150.00;
            case NEGOCIOS:
                return 200.00;
            case LUXO:
                return 300.00;
            case ROMANCE:
                return 400.00;
            case FAMILIA:
                return 160.00;
            case LONGA_ESTADIA:
                return 100.00;
            case VIP:
                return 500.00;
            case COBERTURA:
                return 800.00;
            default:
                return 0.0;
        }
    }
}