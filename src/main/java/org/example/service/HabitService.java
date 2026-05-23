package org.example.service;

import org.example.model.Habit;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HabitService {

    private final Map<String, Habit> habits = new HashMap<>();

    public void createHabit(String name) {
        habits.put(name, new Habit(name));
    }

    public boolean deleteHabit(String name) {
        return habits.remove(name) != null;
    }

    public boolean checkHabit(String name) {
        Habit habit = habits.get(name);
        if (habit != null) {
            habit.addCheckpoint();
            return true;
        }
        return false;
    }

    public List<Habit> getAllHabits() {
        return new ArrayList<>(habits.values());
    }

    public Habit getHabit(String name) {
        return habits.get(name);
    }
}