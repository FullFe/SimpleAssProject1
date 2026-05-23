package org.example.app.model;

import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class HabitDTO implements Serializable {
    final private String name;
    @Setter
    private ArrayList<LocalDateTime> dates;

    public ArrayList<LocalDateTime> getDates() {
        return dates;
    }

    public String getName() {
        return name;
    }


    public HabitDTO(String name) {
        this.name = name;
        this.dates = new ArrayList<>();
    }

}
