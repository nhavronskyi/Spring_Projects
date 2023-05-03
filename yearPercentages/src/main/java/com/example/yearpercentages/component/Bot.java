package com.example.yearpercentages.component;


import com.example.yearpercentages.config.TelegramProps;
import com.example.yearpercentages.dao.UserDao;
import com.example.yearpercentages.user.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.DecimalFormat;
import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final UserDao userDao;
    private final TelegramProps telegramProps;

    @Override
    public String getBotUsername() {
        return telegramProps.getName();
    }

    @Override
    public String getBotToken() {
        return telegramProps.getToken();
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String usersMessage = message.getText();

        User user = new User(message.getChatId(), message.getChat().getUserName(), true);
        if (usersMessage.equals("/start")) user.setStarted(true);
        else if (usersMessage.equals("/stop")) user.setStarted(false);
        userDao.save(user);
    }

    @Scheduled(cron = "0 0 8 * * *") // sends a message every day at 08:00 AM
    private void showDays() {
        userDao.findAllByIsStartedIsTrue()
                .forEach(user -> sendApiMethodAsync(showMessage(showDateInPercentages(), user.getId())));
    }

    private String showDateInPercentages() {
        double percentage = LocalDate.now().getDayOfYear() / 365.0 * 100.0;

        return new DecimalFormat("###.#").format(percentage) + "% / 100%  -> "
                + LocalDate.now().getDayOfYear() + " / 365";
    }

    private SendMessage showMessage(String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);

        sendMessage.setChatId(chatId);

        return sendMessage;
    }
}
