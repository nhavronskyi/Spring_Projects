package com.example.yearpercentages.botService;


import com.example.yearpercentages.component.Bot;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class BotService {

    private final Bot botService;

    public BotService(Bot botService) {
        this.botService = botService;
    }
    @SneakyThrows
    public void startBot(){
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(botService);
    }
}
