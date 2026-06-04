package org.example.dto.habitRequestNResponses;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class HabitRequest {
    @NotBlank(message = "Название привычки не должно быть пустым")
    @Pattern(regexp = "[А-Яа-яёЁ]+")
    private String name;
}
