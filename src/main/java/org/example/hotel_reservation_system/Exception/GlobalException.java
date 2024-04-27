package org.example.hotel_reservation_system.Exception;

public class GlobalException extends RuntimeException{

    public GlobalException(String message) {
        super(message);
    }

    public static class ReservationNotFoundException extends GlobalException {
        public ReservationNotFoundException(String message) {
            super(message);
        }
    }

    public static class ClienteNotFoundException extends GlobalException {
        public ClienteNotFoundException(String message) {
            super(message);
        }
    }

}
