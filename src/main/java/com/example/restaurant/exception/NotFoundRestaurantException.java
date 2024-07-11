package com.example.restaurant.exception;

import lombok.Getter;

@Getter
public class NotFoundRestaurantException extends RuntimeException {

    private final String type;

    private final String field;

    private final Object value;

    public NotFoundRestaurantException(String type, String field, Object value) {
        super("Entity not found");
        this.type = type;
        this.field = field;
        this.value = value;
    }
}
