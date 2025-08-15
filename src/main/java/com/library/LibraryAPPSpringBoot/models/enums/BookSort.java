package com.library.LibraryAPPSpringBoot.models.enums;


import lombok.Getter;

@Getter
public enum BookSort {
    NAME("name"),
    AUTHOR("author"),
    YEAR("year"),
    CREATED_AT("createdAt");

    private final String dbField;

    BookSort(String dbField) {
        this.dbField = dbField;
    }

    public static String resolve(String sortBy) {
        for (BookSort field : values()) {
            if (field.name().equalsIgnoreCase(sortBy)) {
                return field.getDbField();
            }
        }
        return NAME.getDbField();
    }
}
