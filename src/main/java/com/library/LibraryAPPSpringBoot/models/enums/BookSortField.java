package com.library.LibraryAPPSpringBoot.models.enums;


import lombok.Getter;import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

@Getter
public enum BookSortField {
    NAME("name"),
    AUTHOR("author"),
    YEAR("year"),
    CREATED_AT("createdAt");

    private final List<String> fields;

    BookSortField(String... fields) {
        this.fields = List.of(fields);
    }
    public Sort toSort (Direction dir) {
        return Sort.by(fields.stream()
                .map(field -> new Order(dir, field))
                .toList());
    }
}
