package ru.lukyanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.lukyanov.repository")
@ComponentScan("ru/lukyanov")
public class PhoneFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneFinderApplication.class, args);
    }
}