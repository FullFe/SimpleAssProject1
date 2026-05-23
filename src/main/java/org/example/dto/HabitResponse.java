package org.example.app.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class HabitResponse {
    private final String name;
    private final List<LocalDateTime> dates;
    private int totalCheckpoints; // количество отметок

    public HabitResponse(String name, List<LocalDateTime> dates) {
        this.name = name;
        this.dates = dates;
        totalCheckpoints = dates.size();
    }
}
