package org.example.storage;

import org.example.model.Habit;
import org.example.model.HabitDTO;

import java.util.HashMap;

public interface Saveable {
    void save(HashMap<String, HabitDTO> habits, String variable);

    HashMap<String, Habit> load(String variable);
}
