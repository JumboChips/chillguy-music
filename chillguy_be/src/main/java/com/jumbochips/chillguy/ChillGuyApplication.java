package com.jumbochips.chillguy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChillGuyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChillGuyApplication.class, args);
    }

}
