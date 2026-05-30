package org.example.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HabitSuccessResponse extends HabitResponse{
    private final String name;
    private final List<LocalDateTime> dates;
    private int totalCheckpoints; // количество отметок

    public HabitSuccessResponse(String name, List<LocalDateTime> dates) {
        this.name = name;
        this.dates = new ArrayList<>(dates);
        totalCheckpoints = dates.size();
    }
}
