package org.example.service;

import org.example.model.Habit;
import org.example.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void createHabit(String name) {
        Habit habit = new Habit(name);
        habitRepository.save(habit);
    }

    public boolean deleteHabit(String name) {
        Optional<Habit> habit = habitRepository.findByName(name);
        if (habit.isPresent()) {
            habitRepository.delete(habit.get());
            return true;
        }
        return false;
    }

    public boolean checkHabit(String name) {
        Optional<Habit> optionalHabit = habitRepository.findByName(name);
        if (optionalHabit.isPresent()) {
            Habit habit = optionalHabit.get();
            habit.addCheckpoint();
            habitRepository.save(habit);
            return true;
        }
        return false;
    }

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public Habit getHabit(String name) {
        return habitRepository.findByName(name).orElse(null);
    }
}