package com.example.yearpercentages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YearPercentagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(YearPercentagesApplication.class, args);
    }

}
