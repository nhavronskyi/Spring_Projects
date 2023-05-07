package com.example.yearpercentages.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "telegram")
@Component
@Getter
@Setter
public class TelegramProps {
    private String token;
    private String name;
}
