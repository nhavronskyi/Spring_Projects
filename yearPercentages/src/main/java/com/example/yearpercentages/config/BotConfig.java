package com.example.yearpercentages.config;


import com.example.yearpercentages.service.Bot;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {


    private final Bot botService;

    public BotConfig(Bot botService) {
        this.botService = botService;
    }

    TelegramBotsApi telegramBotsApi;

    @Bean
    @SneakyThrows
    public void startBot(){
        telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(botService);
    }
}
