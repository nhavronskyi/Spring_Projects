package com.example.yearpercentages.component;


import com.example.yearpercentages.config.TelegramProps;
import com.example.yearpercentages.repository.UserRepository;
import com.example.yearpercentages.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private UserRepository userRepository;

    private long chatId;

    private final TelegramProps telegramProps;

    @Override
    public String getBotUsername() {
        return telegramProps.getName();
    }
    @Override
    public String getBotToken() {
        return telegramProps.getToken();
    }

    private boolean isStarted = true;
    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        chatId = update.getMessage().getChatId();
        userRepository.save(new User(chatId,update.getMessage().getChat().getUserName(),isStarted));
        if(isStarted) {
            if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
                showDays();
            }
            // TODO: 27.04.2023 make it work
            else if (update.hasMessage() && update.getMessage().getText().equals("/stop")) {
                isStarted = false;
                userRepository.save(new User(chatId,update.getMessage().getChat().getUserName(),isStarted));
                sendApiMethodAsync(showMessage("final"));
            }
        }
    }

    @Scheduled(fixedRate = 3_000)
    public void showDays(){
        sendApiMethodAsync(showMessage(showDateInPercentages()));
    }


    private String showDateInPercentages(){
        double percentage = LocalDate.now().getDayOfYear() /365.0 * 100.0;

        return new DecimalFormat("###.#").format(percentage) + "% / 100%  -> "
                + LocalDate.now().getDayOfYear() + " / 365";
    }

    private SendMessage showMessage(String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);

        sendMessage.setChatId(chatId);

        return sendMessage;
    }
}
