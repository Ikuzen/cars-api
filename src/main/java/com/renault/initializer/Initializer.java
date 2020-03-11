package com.renault.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com.renault")
public class Initializer {
    private final DatabaseInitializerService service;

    Initializer(DatabaseInitializerService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Initializer.class, args);
    }

    @PostConstruct
    void init() {
        service.initializeDB();
    }
}
