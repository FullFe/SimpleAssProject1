package org.example.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Habit{
    final private String name;
    final private ArrayList<LocalDateTime> dates;

    public ArrayList<LocalDateTime> getDates() {
        return dates;
    }

    public String getName() {
        return name;
    }


    public Habit(String name) {
        this.name = name;
        this.dates = new ArrayList<>();
    }

    public Habit(String name, ArrayList<LocalDateTime> dates) {
        this.name = name;
        this.dates = dates;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Habit habit = (Habit) o;
        return Objects.equals(name, habit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Habit{" +
                "name='" + name + '\'' +
                ", dates=" + dates +
                "}\n";
    }

    public void addCheckpoint(){
        dates.add(LocalDateTime.now());
    }

    public void deleteCheckpoint(int i){
        if(i <= dates.size() - 1){
            dates.remove(i);
        }
    }


}
