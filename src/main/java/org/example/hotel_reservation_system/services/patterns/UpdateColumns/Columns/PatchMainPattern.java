package org.example.hotel_reservation_system.services.patterns.UpdateColumns.Columns;

import org.example.hotel_reservation_system.services.patterns.UpdateColumns.IColumn;

public class PatchMainPattern {
    public static class NamePatch implements IColumn {
        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }

        @Override
        public String updateColumn(String value, String newValue) {
            return "Nome: " + value + " -> " + newValue;
        }
    }

    public static class EmailPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "Email: " + value + " -> " + newValue;
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }
    }

    public static class CpfPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "CPF: " + value + " -> " + newValue;
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }
    }

    public static class RgPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "RG: " + value + " -> " + newValue;
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }
    }

    public static class EnderecoPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "Endereço: " + value + " -> " + newValue;
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }
    }

    public static class CepPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "CEP: " + value + " -> " + newValue;
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }
    }

    public static class NumeroPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "Número: " + value + " -> " + newValue;
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }
    }

    public static class EstadoPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "Estado: " + value + " -> " + newValue;
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }
    }

    public static class PaisPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "País: " + value + " -> " + newValue;
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }
    }

    public static class DataNascimentoPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "Data de nascimento: " + value + " -> " + newValue;
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return null;
        }
    }

    public static class StatusPatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "";
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return "Status: " + text + " -> " + newText;
        }
    }

    public static class RolePatch implements IColumn {
        @Override
        public String updateColumn(String value, String newValue) {
            return "";
        }

        @Override
        public String getColumnType(Enum text, Enum newText) {
            return "Role: " + text + " -> " + newText;
        }
    }
}
