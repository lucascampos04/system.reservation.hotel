package org.example.hotel_reservation_system.model.Clientes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.model.Employees.RegisterEntity;
import org.example.hotel_reservation_system.util.BaseEntity;
import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClientesEntity extends BaseEntity {
    @Id
    private String Id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf", length = 11, unique = true)
    private Character cpf;

    @Column(name = "rg", length = 15, unique = true)
    private Character rg;   

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cep", length = 8)
    private Character cep;

    @Column(name = "numero")
    private String numero;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimeo; 

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'DESATIVADO'")
    private StatusEnum status;

    @Column(name = "data_registro")
    private LocalDateTime data_registro;

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
