package com.library.LibraryAPPSpringBoot.models.enums;

public enum FilmSortField {
    NAME("name"),
    DIRECTOR("director"),
    YEAR("year"),
    CREATED_AT("createdAt");

    private final String dbField;

    FilmSortField(String dbField) {
        this.dbField = dbField;
    }

    public String getDbField() {
        return dbField;
    }
}


