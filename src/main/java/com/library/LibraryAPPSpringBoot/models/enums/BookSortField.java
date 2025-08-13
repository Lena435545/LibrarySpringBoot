package com.library.LibraryAPPSpringBoot.models.enums;


public enum BookSortField {
    NAME("name"),
    AUTHOR("author"),
    YEAR("year"),
    CREATED_AT("createdAt");

    private final String prop;

    BookSortField(String prop) {
        this.prop = prop;
    }

    public String prop() {
        return prop;
    }

    public static String resolve(String input) {
        for (BookSortField f : values()) {
            if (f.name().equalsIgnoreCase(input)) {
                return f.prop();
            }
        }
        return NAME.prop();
    }
}
