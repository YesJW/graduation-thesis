package com.example.graduationthesis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GraduationThesisApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationThesisApplication.class, args);
    }

}
