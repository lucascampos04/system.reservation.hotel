package org.example.hotel_reservation_system.model.Clientes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "clientes")
public class ClientesEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "rg", unique = true)
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
    private LocalDate dataNascimeo; 

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'DESATIVADO'")
    private StatusEnum status;

    @Column(name = "data_registro")
    private LocalDateTime data_registro;
}
