package com.library.LibraryAPPSpringBoot.models.enums;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

public enum JournalSortField {
    NAME("name"),
    THEMATIC("thematic"),
    DATE("year", "month"),
    CREATED_AT("createdAt");

    private final List<String> fields;

    JournalSortField(String ... fields){
      this.fields = List.of(fields);
    }

    public Sort toSort(Direction dir) {
        return Sort.by(fields.stream()
                .map(field -> new Order(dir, field))
                .toList());
    }
}
