package org.example.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Отключаем защиту CSRF, так как для REST API (без сессий в браузере) она не нужна
                .csrf(csrf -> csrf.disable())

                // Настраиваем правила доступа
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Доступ к H2 консоли
                        .requestMatchers("/api/auth/register").permitAll() // Регистрация открыта для всех

                        // Настройка ролей:
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Сюда может зайти ТОЛЬКО админ
                        .requestMatchers("/api/habits/**").hasAnyRole("USER", "ADMIN") // Сюда могут все авторизованные

                        .anyRequest().authenticated() // Все остальные эндпоинты требуют логина
                )

                // Включаем HTTP Basic аутентификацию (Postman будет присылать логин/пароль в заголовках)
                .httpBasic(Customizer.withDefaults())

                // Разрешаем отображение консоли H2 во фреймах
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Хэшер паролей
    }
}
