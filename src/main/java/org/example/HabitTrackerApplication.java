package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
/*
    Автоматический сканер Spring Boot искал репозитории только внутри org.example.app.*.
Ваши репозитории в org.example.repository он просто проигнорировал,
так как этот пакет находится «выше» и в стороне. Аннотация scanBasePackages = "org.example"
расширила область поиска для обычных сервисов (поэтому HabitService нашелся),
но она не влияет на скрытые автоконфигурации для JPA
@EnableJpaRepositories(basePackages = "org.example.repository") // Явно указываем где искать репозитории
@EntityScan(basePackages = "org.example.model") // Явно указываем где искать ваши модели/сущности

Оставлю это как напоминалку непонималку
 */
public class HabitTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HabitTrackerApplication.class, args);
    }
}
