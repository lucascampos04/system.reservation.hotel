package org.example.hotel_reservation_system.util;

import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEntity {

    public abstract LocalDateTime getDataRegistro();
    public abstract void setDataRegistro(LocalDateTime dataRegistro);

    public abstract String getId();
    public abstract void setId(String string);

    /**
     * Método chamado antes da persistência da entidade.
     * Configura automaticamente a data de registro para o valor atual
     * se ainda não estiver definida. Além disso, configura o ID como
     * um UUID aleatório se ainda não estiver definido.
     *
     * @throws IllegalStateException Se a data de registro já estiver definida.
     * @throws IllegalStateException Se o ID já estiver definido.
     */
    public void prePersist() {
        if (getDataRegistro() == null) {
            setDataRegistro(LocalDateTime.now());
        } else {
            throw new IllegalStateException("A data de registro já está definida");
        }

        if (getId() == null) {
            setId(UUID.randomUUID().toString());
        } else {
            throw new IllegalStateException("O Id já está definido");
        }
    }
}
