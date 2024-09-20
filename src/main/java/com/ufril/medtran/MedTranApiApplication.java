package com.ufril.medtran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedTranApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedTranApiApplication.class, args);
    }
}
