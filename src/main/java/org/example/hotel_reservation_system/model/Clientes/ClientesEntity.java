package org.example.hotel_reservation_system.model.Clientes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClientesEntity {
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
    

    /**
     * Método chamado antes da persistência da entidade.
     * Configura automaticamente a data de registro para o valor atual
     * se ainda não estiver definida. Além disso, configura o ID como
     * um UUID aleatório se ainda não estiver definido.
     *
     * @throws IllegalStateException Se a data de registro já estiver definida.
     * @throws IllegalStateException Se o ID já estiver definido.
     */
    @PrePersist
    public void prePersist(){
        if (data_registro == null){
            data_registro = LocalDateTime.now();
        } else {
            throw new IllegalStateException("A data de registro já está definida");
        }

        if (Id == null){
            Id = UUID.randomUUID().toString();
        } else {
            throw new IllegalStateException("O Id já está definido");
        }
    }

}
