package org.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example")
public class HabitTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HabitTrackerApplication.class);
    }
}
