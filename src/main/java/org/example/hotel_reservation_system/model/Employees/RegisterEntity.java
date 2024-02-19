package org.example.hotel_reservation_system.model.Employees;

import jakarta.persistence.*;
import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.util.BaseEntity;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
public class RegisterEntity extends BaseEntity {

    @Id
    private String Id;
    @Column(name = "inicio_checkin")
    private LocalDateTime inicio_checkin;
    @Column(name = "final_checkin")
    private LocalDateTime final_checkin;
    @Column(name = "forma_pagamento")
    private String forma_pagamento;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'DESATIVADO'")
    private StatusEnum status;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "pacote")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'DESATIVADO'")
    private PacoteEnum pacoteEnum;
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_registro")
    private LocalDateTime data_registro;
    @ManyToOne
    private ClientesEntity clientesEntity;

    @Override
    public LocalDateTime getDataRegistro() {
        return data_registro;
    }

    @Override
    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.data_registro = dataRegistro;
    }

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public void setId(String id) {
        this.Id = id;
    }
}
