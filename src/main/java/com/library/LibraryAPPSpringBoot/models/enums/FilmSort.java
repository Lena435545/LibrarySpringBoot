package com.library.LibraryAPPSpringBoot.models.enums;

import lombok.Getter;

@Getter
public enum FilmSort {
    NAME("name"),
    DIRECTOR("director"),
    YEAR("year"),
    CREATED_AT("createdAt");

    private final String dbField;

    FilmSort(String dbField) {
        this.dbField = dbField;
    }

}


