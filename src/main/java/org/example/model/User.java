package org.example.model;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String pass;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habit> habits;

    @Enumerated(EnumType.STRING) // По приколу добавить шифрование
    @Setter
    private Rights rights;

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }
}
