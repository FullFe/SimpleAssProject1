package org.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "org.example")
/*
    Автоматический сканер Spring Boot искал репозитории только внутри org.example.app.*.
Ваши репозитории в org.example.repository он просто проигнорировал,
так как этот пакет находится «выше» и в стороне. Аннотация scanBasePackages = "org.example"
расширила область поиска для обычных сервисов (поэтому HabitService нашелся),
но она не влияет на скрытые автоконфигурации для JPA
 */
@EnableJpaRepositories(basePackages = "org.example.repository") // Явно указываем где искать репозитории
@EntityScan(basePackages = "org.example.model") // Явно указываем где искать ваши модели/сущности
public class HabitTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HabitTrackerApplication.class, args);
    }
}
