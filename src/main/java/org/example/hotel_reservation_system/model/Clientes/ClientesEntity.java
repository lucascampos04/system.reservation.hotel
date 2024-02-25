package org.example.hotel_reservation_system.model.Clientes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "clientes")
public class ClientesEntity{
    @Id
    private Long Id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cep")
    private String cep;

    @Column(name = "numero")
    private String numero;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @Column(name = "data_nascimento")
    private String data_nascimento;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'DESATIVADO'")
    private StatusEnum status;

    @Column(name = "data_registro")
    private LocalDateTime data_registro;

    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private RolesEnum role;
    @JsonIgnore
    public StatusEnum getStatus() {
        return status;
    }


}
