package com.example.yearpercentages.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "application.properties")
@Configuration
@Getter
public class DataConfig {
    @Value("${telegram.token}")
    private String token;
    @Value("${telegram.name}")
    private String name;
    @Value("${telegram.childId}")
    private Long childId;

}
