package com.example.yearpercentages.component;


import com.example.yearpercentages.config.TelegramProps;
import com.example.yearpercentages.dao.UserDao;
import com.example.yearpercentages.user.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.concurrent.ScheduledFuture;


@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final UserDao userDao;
    private final TelegramProps telegramProps;

    private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;
    private long chatId;

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
        chatId = message.getChatId();
        User user = new User(chatId, message.getChat().getUserName(), true);
        userDao.save(user);

        String usersMessage = message.getText();

        if (usersMessage.equals("/start")) {
            userDao.update(true, chatId);
            if (scheduledFuture == null || scheduledFuture.isCancelled())
                scheduledFuture = taskScheduler.scheduleAtFixedRate(this::showDays, 24*60*60_000);
        } else if (usersMessage.equals("/stop")) {
            if (!scheduledFuture.isCancelled()) scheduledFuture.cancel(true);
            userDao.update(false, chatId);
        }
    }

    public void showDays() {
        sendApiMethodAsync(showMessage(showDateInPercentages()));
    }


    private String showDateInPercentages() {
        double percentage = LocalDate.now().getDayOfYear() / 365.0 * 100.0;

        return new DecimalFormat("###.#").format(percentage) + "% / 100%  -> "
                + LocalDate.now().getDayOfYear() + " / 365";
    }

    private SendMessage showMessage(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);

        sendMessage.setChatId(chatId);

        return sendMessage;
    }
}
