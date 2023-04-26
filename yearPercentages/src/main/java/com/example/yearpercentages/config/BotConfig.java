package com.example.yearpercentages.config;


import com.example.yearpercentages.botService.BotService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BotConfig {

    BotService botService;
    @Bean
    public void starter(){
        botService.startBot();
    }
}
