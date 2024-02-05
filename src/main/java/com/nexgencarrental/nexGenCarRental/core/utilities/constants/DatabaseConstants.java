package com.nexgencarrental.nexGenCarRental.core.utilities.constants;

public final class DatabaseConstants {
    public static final String TABLE_NAME = "your_table_name";
    public static final String ID_COLUMN = "id";
    public static final String CREATED_DATE_COLUMN = "created_date";
    public static final String UPDATED_DATE_COLUMN = "updated_date";

    // Diğer veritabanı detayları...

    private DatabaseConstants() {
        throw new IllegalStateException("Utility class");
    }

}
