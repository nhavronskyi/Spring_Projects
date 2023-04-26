package com.example.yearpercentages.service;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;


@AllArgsConstructor
@Service
public class BotService extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "actualDateInPercentageBot";
    }
    @Override
    public String getBotToken() {
        return "6104036093:AAFiKoIJHwhncpW55H4mN88LfrTmx_uzURc";
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        while (true) {
            if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
                Thread thread = new Thread(() -> sendApiMethodAsync(showMessage(update, showDateInPercentages())));
                thread.start();
                Thread.sleep(2000);

            }
            // here is a problem telegram does not see a message "/stop"
            if (update.hasMessage() && update.getMessage().getText().equals("/stop")) {
                showMessage(update, "break");
                break;
            }
        }
    }

    private String showDateInPercentages(){
        double percentage = LocalDate.now().getDayOfYear() /365.0 * 100.0;

        return new DecimalFormat("###.#").format(percentage) + "% / 100%  -> "
                + LocalDate.now().getDayOfYear() + " / 365";
    }

    private SendMessage showMessage(Update update, String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);

        sendMessage.setChatId(Objects.requireNonNull(getChildId(update)));

        return sendMessage;
    }

    private Long getChildId(Update update){
        return update.hasMessage() ? update.getMessage().getFrom().getId() : null;
    }
}
