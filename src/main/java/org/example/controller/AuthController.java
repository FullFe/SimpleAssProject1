package org.example.controller;

import org.example.model.Rights;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Проверяем, занят ли логин
        if (userRepository.findByLogin(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Ошибка: Логин уже занят!");
        }

        // ОБЯЗАТЕЛЬНО шифруем пароль перед сохранением
        user.setPass(passwordEncoder.encode(user.getPassword()));

        // По умолчанию при регистрации даем роль обычного пользователя
        user.setRole(Rights.USER);

        userRepository.save(user);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован!");
    }
}
