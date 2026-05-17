package org.example.storage;

import org.example.model.Habit;
import org.example.model.HabitDTO;

import java.io.*;
import java.util.HashMap;

public class HabitStorage {

    final Saveable saveable;

    public HabitStorage(Saveable saveable) {
        this.saveable = saveable;
    }


    public void save(HashMap<String, HabitDTO> habits, String filePath){
        saveable.save(habits, filePath);
    }

    public HashMap<String, Habit> load(String filePath){
        return saveable.load(filePath);
    }
}

