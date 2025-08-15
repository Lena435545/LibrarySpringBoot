package com.library.LibraryAPPSpringBoot.models.enums;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

public enum JournalSort {
    NAME("name"),
    THEMATIC("thematic"),
    DATE("year", "month"),
    CREATED_AT("createdAt");

    private final List<String> dbFields;

    JournalSort(String ... dbFields){
      this.dbFields = List.of(dbFields);
    }

    public Sort toSort(Direction dir) {
        return Sort.by(dbFields.stream()
                .map(field -> new Sort.Order(dir, field))
                .toList());
    }
}
