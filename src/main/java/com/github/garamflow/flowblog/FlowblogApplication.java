package com.github.garamflow.flowblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FlowblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowblogApplication.class, args);
    }

}
