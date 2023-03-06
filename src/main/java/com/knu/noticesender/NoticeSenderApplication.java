package com.knu.noticesender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NoticeSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoticeSenderApplication.class, args);
    }

}
