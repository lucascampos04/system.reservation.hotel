package org.example.hotel_reservation_system.model.Clientes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.model.Plano.PlanoEntity;
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

    @OneToOne
    @JoinColumn(name = "plano_id")
    private PlanoEntity plano;
    @JsonIgnore
    public StatusEnum getStatus() {
        return status;
    }

    public ClientesEntity(){

    }
    public ClientesEntity(Long id, String nome, String email,
                          String cpf, String rg, String endereco,
                          String cep, String numero,
                          String estado, String pais, String data_nascimento,
                          StatusEnum status, LocalDateTime data_registro,
                          RolesEnum role, PlanoEntity plano) {
        Id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.cep = cep;
        this.numero = numero;
        this.estado = estado;
        this.pais = pais;
        this.data_nascimento = data_nascimento;
        this.status = status;
        this.data_registro = data_registro;
        this.role = role;
        this.plano = plano;
    }
}
