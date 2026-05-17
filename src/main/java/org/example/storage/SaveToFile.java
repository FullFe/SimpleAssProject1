package org.example.storage;

import org.example.model.Habit;
import org.example.model.HabitDTO;

import java.io.*;
import java.util.HashMap;

import static org.example.service.HabitService.DtoToHabit;

public class SaveToFile implements Saveable{
    @Override
    public void save(HashMap<String, HabitDTO> habits, String filePath){
        try(FileOutputStream fs = new FileOutputStream(filePath);
            ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(habits);
        } catch (IOException e){
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public HashMap<String, Habit> load(String filePath){
        HashMap<String, HabitDTO> result;
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            result = (HashMap<String, HabitDTO>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.err.println("Ошибка сохранения: " + e.getMessage());
            result = null;
        }
        return DtoToHabit(result);
    }
}
