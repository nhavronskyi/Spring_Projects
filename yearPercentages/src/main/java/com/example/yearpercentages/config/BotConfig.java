package com.example.yearpercentages.config;


import com.example.yearpercentages.component.Bot;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@AllArgsConstructor
public class BotConfig {

    private final Bot bot;
    @Bean
    @SneakyThrows
    public void starter(){
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }


}
