package org.example.service;

import org.example.model.Habit;
import org.example.model.HabitDTO;
import org.example.storage.HabitStorage;

import java.util.*;

public class HabitService {

    final private HabitStorage storage;
    final private HashMap<String, Habit> habits;
    final private String path;

    public HabitService(HabitStorage storage, String path) {
        this.storage = storage;

        HashMap<String, Habit> loaded = storage.load(path);
        this.habits = loaded != null ? loaded : new HashMap<>();

        this.path = path;
    }

    public void createHabit(String name){
        habits.put(name, new Habit(name));
        storage.save(habitToDto(habits), path);
    }

    public boolean deleteHabit(String name){
        Habit habit = habits.remove(name);
        storage.save(habitToDto(habits), path);
        return !Objects.isNull(habit);
    }

    public boolean checkHabit(String name){
        Habit habit = habits.get(name);

        if(!Objects.isNull(habit)){
            habit.addCheckpoint();
            storage.save(habitToDto(habits), path);
            return true;
        }
        storage.save(habitToDto(habits), path);
        return false;
    }

    public String statsOut(){
        StringBuilder tmp = new StringBuilder();
        for (Map.Entry<String, Habit> stringHabitEntry : habits.entrySet()) {
            tmp.append(stringHabitEntry.getValue());
        }
        return tmp.toString();
    }

    private HashMap<String, HabitDTO> habitToDto(HashMap<String, Habit> habits){
        HashMap<String, HabitDTO> tmp = new HashMap<>();
        for (Map.Entry<String, Habit> stringHabitEntry : habits.entrySet()) {
            HabitDTO dto = new HabitDTO(stringHabitEntry.getKey());
            dto.setDates(stringHabitEntry.getValue().getDates());
            tmp.put(stringHabitEntry.getKey(), dto);
        }
        return tmp;
    }

    public static HashMap<String, Habit> DtoToHabit(HashMap<String, HabitDTO> habits){
        HashMap<String, Habit> tmp = new HashMap<>();
        for (Map.Entry<String, HabitDTO> stringHabitEntry : habits.entrySet()) {
            Habit entry = new Habit(stringHabitEntry.getKey(), stringHabitEntry.getValue().getDates());
            tmp.put(stringHabitEntry.getKey(), entry);
        }
        return tmp;
    }
}
