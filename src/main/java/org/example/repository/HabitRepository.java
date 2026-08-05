package org.example.repository;

import org.example.model.Habit;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    Optional<Habit> findByNameAndUser(String name, User user);
    void deleteByName(String name);
    List<Habit> findByUser(User user);
}