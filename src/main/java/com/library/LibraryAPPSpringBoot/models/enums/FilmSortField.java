package com.library.LibraryAPPSpringBoot.models.enums;

import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.List;


@Getter
public enum FilmSortField {
    NAME("name"),
    DIRECTOR("director"),
    YEAR("year"),
    CREATED_AT("createdAt");

    private final List<String> fields;

    FilmSortField(String... fields) {
        this.fields = List.of(fields);
    }

    public Sort toSort(Direction direction) {
        return Sort.by(fields.stream()
                .map(field -> new Order(direction, field))
                .toList());
    }
}


