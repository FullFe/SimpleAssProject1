package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.HabitRequest;
import org.example.model.Habit;
import org.example.service.HabitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HabitController.class)
class HabitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HabitService habitService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createHabit_ShouldReturn201() throws Exception {
        HabitRequest request = new HabitRequest();
        request.setName("Бег");

        mockMvc.perform(post("/api/habits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllHabits_ShouldReturnList() throws Exception {
        when(habitService.getAllHabits()).thenReturn(List.of(new Habit("Бег")));

        mockMvc.perform(get("/api/habits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Бег"));
    }

    @Test
    void getHabit_ShouldReturn404_WhenNotFound() throws Exception {
        when(habitService.getHabit("Несуществующая")).thenReturn(null);

        mockMvc.perform(get("/api/habits/Несуществующая"))
                .andExpect(status().isNotFound());
    }
}