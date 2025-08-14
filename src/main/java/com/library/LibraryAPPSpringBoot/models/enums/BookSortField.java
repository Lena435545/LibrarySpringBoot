package com.library.LibraryAPPSpringBoot.models.enums;


public enum BookSortField {
    NAME("name"),
    AUTHOR("author"),
    YEAR("year"),
    CREATED_AT("createdAt");

    private final String dbField;

    BookSortField(String dbField) {
        this.dbField = dbField;
    }

    public String getDbField() {
        return dbField;
    }

    public static String resolve(String sortBy) {
        for (BookSortField field : values()) {
            if (field.name().equalsIgnoreCase(sortBy)) {
                return field.getDbField();
            }
        }
        return NAME.getDbField();
    }
}
