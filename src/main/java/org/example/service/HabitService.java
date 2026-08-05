package org.example.service;

import org.example.model.Habit;
import org.example.model.User;
import org.example.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public void createHabit(String name, User user) {
        Habit habit = new Habit(name);
        habit.setUser(user);
        habitRepository.save(habit);
    }

    public boolean deleteHabit(String name, User user) {
        Optional<Habit> habit = habitRepository.findByNameAndUser(name, user);
        if (habit.isPresent()) {
            habitRepository.delete(habit.get());
            return true;
        }
        return false;
    }

    public boolean checkHabit(String name, User user) {
        Optional<Habit> optionalHabit = habitRepository.findByNameAndUser(name, user);
        if (optionalHabit.isPresent()) {
            Habit habit = optionalHabit.get();
            habit.addCheckpoint();
            habitRepository.save(habit);
            return true;
        }
        return false;
    }

    public List<Habit> getAllHabits(User user) {
        return habitRepository.findByUser(user);
    }

    public Habit getHabit(String name, User user) {
        return habitRepository.findByNameAndUser(name, user).orElse(null);
    }
}