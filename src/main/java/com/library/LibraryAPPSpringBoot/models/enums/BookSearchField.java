package com.library.LibraryAPPSpringBoot.models.enums;

import lombok.Getter;

@Getter
public enum BookSearchField {
    TITLE("name"),
    AUTHOR("author");

    private final String dbField;

    BookSearchField(String dbField) {
        this.dbField = dbField;
    }
}
