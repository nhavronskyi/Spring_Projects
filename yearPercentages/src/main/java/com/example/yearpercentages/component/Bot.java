package com.example.yearpercentages.component;


import com.example.yearpercentages.config.DataConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;


@Component
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final DataConfig dataConfig;


    @NonNull
    private ThreadPoolTaskScheduler scheduler;
    @Override
    public String getBotUsername() {
        return dataConfig.getName();
    }
    @Override
    public String getBotToken() {
        return dataConfig.getToken();
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        this.update = update;
        showUpdated();

        showMessage("end");
    }

    private Update update;

    @SneakyThrows
    @Scheduled(fixedRate = 5000)
    public void showUpdated(){
        if(update!=null) {
            if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
                sendApiMethodAsync(showMessage(showDateInPercentages()));
            } else if (update.hasMessage() && update.getMessage().getText().equals("/stop")) {
                sendApiMethodAsync(showMessage("stopped"));
                scheduler.shutdown();
            }
        }
    }


    private String showDateInPercentages(){
        double percentage = LocalDate.now().getDayOfYear() /365.0 * 100.0;

        return new DecimalFormat("###.#").format(percentage) + "% / 100%  -> "
                + LocalDate.now().getDayOfYear() + " / 365";
    }

    private SendMessage showMessage(String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);

        sendMessage.setChatId(Objects.requireNonNull(dataConfig.getChildId()));

        return sendMessage;
    }

}
