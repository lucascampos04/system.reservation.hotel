package org.example.hotel_reservation_system.services.patterns.FieldsExisting;

public class MainFieldExisting {

    public static class CpfExisting implements IFiledsExisting{
        @Override
        public String appliPattern(String text) {
            return text;
        }
    }

    public static class RgExisting implements IFiledsExisting{
        @Override
        public String appliPattern(String text) {
            return text;
        }
    }
}
