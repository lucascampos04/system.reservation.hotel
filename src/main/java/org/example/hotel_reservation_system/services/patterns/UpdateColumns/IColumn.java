package org.example.hotel_reservation_system.services.patterns.UpdateColumns;

public interface IColumn {
    String updateColumn(String value, String newValeu);
    String getColumnType(Enum text, Enum newText);
}
