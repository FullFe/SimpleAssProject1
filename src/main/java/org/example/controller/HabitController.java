package org.example.app.controller;

import lombok.RequiredArgsConstructor;
import org.example.app.dto.HabitRequest;
import org.example.app.dto.HabitResponse;
import org.example.app.model.Habit;
import org.example.app.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    // POST /api/habits — создание (тело: {"name": "Чтение"})
    @PostMapping
    public ResponseEntity<String> createHabit(@RequestBody HabitRequest request) {
        habitService.createHabit(request.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Привычка создана: " + request.getName());
    }

    // GET /api/habits — список всех привычек
    @GetMapping
    public ResponseEntity<List<HabitResponse>> getAllHabits() {
        List<HabitResponse> response = habitService.getAllHabits()
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    // GET /api/habits/{name} — получить одну привычку
    @GetMapping("/{name}")
    public ResponseEntity<HabitResponse> getHabit(@PathVariable String name) {
        Habit habit = habitService.getHabit(name);
        if (habit == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toResponse(habit));
    }

    // POST /api/habits/{name}/check — отметить выполнение
    @PostMapping("/{name}/check")
    public ResponseEntity<String> checkHabit(@PathVariable String name) {
        if (habitService.checkHabit(name)) {
            return ResponseEntity.ok("Привычка отмечена: " + name);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /api/habits/{name} — удалить привычку
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteHabit(@PathVariable String name) {
        if (habitService.deleteHabit(name)) {
            return ResponseEntity.ok("Привычка удалена: " + name);
        }
        return ResponseEntity.notFound().build();
    }

    private HabitResponse toResponse(Habit habit) {
        return new HabitResponse(
                habit.getName(),
                new ArrayList<>(habit.getDates())
        );
    }
}
