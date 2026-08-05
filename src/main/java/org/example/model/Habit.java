package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "habits")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "checkpoints",
            joinColumns = @JoinColumn(name = "habit_id")
    )
    @Column(name = "check_date")
    private List<LocalDateTime> dates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    public Habit() {}

    public Habit(String name) {
        this.name = name;
        dates = new ArrayList<>();
    }

    public void addCheckpoint(){
        dates.add(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habit habit = (Habit) o;
        return Objects.equals(name, habit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
