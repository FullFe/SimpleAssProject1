package org.example.service;

import org.example.model.Habit;
import org.example.repository.HabitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HabitServiceTest {

    @Mock
    private HabitRepository habitRepository;

    @InjectMocks
    private HabitService habitService;

    @Test
    void createHabit_ShouldSaveNewHabit() {
        habitService.createHabit("Бег");
        verify(habitRepository, times(1)).save(any(Habit.class));
    }

    @Test
    void checkHabit_ShouldAddCheckpoint_WhenHabitExists() {
        Habit habit = new Habit("Бег");
        when(habitRepository.findByName("Бег")).thenReturn(Optional.of(habit));

        boolean result = habitService.checkHabit("Бег");

        assertTrue(result);
        assertEquals(1, habit.getDates().size());
        verify(habitRepository).save(habit);
    }

    @Test
    void checkHabit_ShouldReturnFalse_WhenHabitNotFound() {
        when(habitRepository.findByName("Несуществующая")).thenReturn(Optional.empty());

        boolean result = habitService.checkHabit("Несуществующая");

        assertFalse(result);
        verify(habitRepository, never()).save(any());
    }

    @Test
    void deleteHabit_ShouldReturnTrue_WhenHabitExists() {
        Habit habit = new Habit("Бег");
        when(habitRepository.findByName("Бег")).thenReturn(Optional.of(habit));

        boolean result = habitService.deleteHabit("Бег");

        assertTrue(result);
        verify(habitRepository).delete(habit);
    }

    @Test
    void deleteHabit_ShouldReturnFalse_WhenHabitNotFound() {
        when(habitRepository.findByName("Несуществующая")).thenReturn(Optional.empty());

        boolean result = habitService.deleteHabit("Несуществующая");

        assertFalse(result);
        verify(habitRepository, never()).delete(any());
    }
}