package org.example.hotel_reservation_system.Exception;

public class ClienteNaoAtivoException  extends RuntimeException{
    public ClienteNaoAtivoException(String message) {
        super("Cliente não está ativo");
    }
    public ClienteNaoAtivoException(String message, Throwable cause) {
        super(message, cause);
    }
    public ClienteNaoAtivoException(Throwable cause) {
        super(cause);
    }

    protected ClienteNaoAtivoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
