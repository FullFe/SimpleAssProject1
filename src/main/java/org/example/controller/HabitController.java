package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.dto.CreateHabitRequest;
import org.example.model.Habit;
import org.example.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping()
    public ResponseEntity<String> createHabit(@RequestBody CreateHabitRequest name){
        habitService.createHabit(name);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Привычка создана " + name);
    }

    @GetMapping()
    public ResponseEntity<List<Habit>> getAllHabits(){
        return ResponseEntity.ok()
                .body(habitService.getAllHabits());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Habit> getHabit(@PathVariable String name){
        Habit habit = habitService.getHabit(name);
        if(Objects.isNull(habit))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok()
                .body(habit);
    }

    @PostMapping("/{name}/check")
    public ResponseEntity<String> checkHabit(@PathVariable String name){
        if(habitService.checkHabit(name))
            return ResponseEntity.ok().body("Habit " + name + " checked");
        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteHabit(@PathVariable String name){
        if(habitService.deleteHabit(name))
            return ResponseEntity.ok().body("Habit " + name + " deleted");
        return  ResponseEntity.notFound().build();
    }

}
