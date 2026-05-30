package org.example.dto;

import lombok.Getter;

@Getter
public class HabitErrorResponse extends HabitResponse{
    private final String message;

    public HabitErrorResponse(String message) {
        this.message = message;
    }
}
