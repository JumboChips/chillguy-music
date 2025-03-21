package com.jumbochips.chillguy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class ChillGuyApplication {

    public static void main(String[] args) {
        log.debug("DEBUG 로그 테스트");
        SpringApplication.run(ChillGuyApplication.class, args);
    }

}
