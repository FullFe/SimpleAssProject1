package org.example.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.example.dto.habitRequestNResponses.HabitRequest;
import org.example.dto.habitRequestNResponses.HabitResponse;
import org.example.dto.habitRequestNResponses.HabitSuccessResponse;
import org.example.exception.HabitNotFoundException;
import org.example.model.Habit;
import org.example.model.User;
import org.example.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
@Validated
public class HabitController {

    private final HabitService habitService;

    @Transactional
    // POST /api/habits — создание (тело: {"name": "Чтение"})
    @PostMapping
    public ResponseEntity<String> createHabit(@Valid @RequestBody HabitRequest request, @AuthenticationPrincipal User currentUser) {

        habitService.createHabit(request.getName(), currentUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Привычка создана: " + request.getName());
    }

    @Transactional
    // POST /api/habits/{name}/check — отметить выполнение
    @PostMapping("/{name}/check")
    public ResponseEntity<String> checkHabit(@Pattern(regexp = "^[А-Яа-яёЁ]+$", message = "Только русские буквы") @PathVariable String name, @AuthenticationPrincipal User currentUser) {
        if (habitService.checkHabit(name, currentUser)) {
            return ResponseEntity.ok("Привычка отмечена: " + name);
        }
        throw new HabitNotFoundException("Ошибка! Привычка '" + name + "' не найдена");
    }

    @Transactional
    // DELETE /api/habits/{name} — удалить привычку
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteHabit(@Pattern(regexp = "^[А-Яа-яёЁ]+$", message = "Только русские буквы") @PathVariable String name, @AuthenticationPrincipal User currentUser) {
        if (habitService.deleteHabit(name, currentUser)) {
            return ResponseEntity.ok("Привычка удалена: " + name);
        }
        throw new HabitNotFoundException("Ошибка! Привычка '" + name + "' не найдена");
    }

    @Transactional(readOnly = true)
    // GET /api/habits — список всех привычек
    @GetMapping
    public ResponseEntity<List<HabitSuccessResponse>> getAllHabits(@AuthenticationPrincipal User currentUser) {
        List<HabitSuccessResponse> response = habitService.getAllHabits(currentUser)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Transactional(readOnly = true)
    // GET /api/habits/{name} — получить одну привычку
    @GetMapping("/{name}")
    public ResponseEntity<? extends HabitResponse> getHabit(@Pattern(regexp = "^[А-Яа-яёЁ]+$", message = "Только русские буквы") @PathVariable String name, @AuthenticationPrincipal User currentUser) {
        Habit habit = habitService.getHabit(name, currentUser);
        if (habit == null) {
            throw new HabitNotFoundException("Ошибка! Привычка '" + name + "' не найдена");
        }
        return ResponseEntity.ok(toResponse(habit));
    }

    private HabitSuccessResponse toResponse(Habit habit) {
        return new HabitSuccessResponse(
                habit.getName(),
                new ArrayList<>(habit.getDates())
        );
    }
}
