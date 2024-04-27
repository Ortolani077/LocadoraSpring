package com.Locadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Locadora"})
@EnableJpaRepositories(basePackages = {"com.Locadora.Repository"})
@EntityScan(basePackages = {"com.Locadora.Model"})
public class LocadoraApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocadoraApplication.class, args);
    }
}
