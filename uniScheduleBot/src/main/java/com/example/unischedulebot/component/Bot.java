package com.example.unischedulebot.component;

import com.example.unischedulebot.service.GService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final TelegramProps telegramProps;

    private final GService gService;
    private Long chatId;

    @Override
    public String getBotUsername() {
        return telegramProps.getUsername();
    }

    @Override
    public String getBotToken() {
        return telegramProps.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            SendMessage sendMessage = buttonManager("Choose option", Map.of(
                    "getSchedule", "getSchedule",
                    "getEvents", "getEvents",
                    "createDemoEvent", "createDemoEvent",
                    "creatEventsForSchedule", "creatEventsForSchedule"
            ), update.getMessage().getChatId());
            sendApiMethodAsync(sendMessage);
            chatId = update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("getSchedule")) {
                String schedule = gService.getSchedule().entrySet()
                        .stream()
                        .map(x -> x.getKey() + " " + x.getValue().stream().map(y -> "\n" + y + "\n").collect(Collectors.joining()) + "\n\n")
                        .collect(Collectors.joining());
                sendApiMethodAsync(sendMessage(schedule, chatId));
            }
            if (update.getCallbackQuery().getData().equals("getEvents"))
                sendApiMethodAsync(sendMessage(gService.getEvents().stream().map(x -> x + "\n\n").collect(Collectors.joining()), chatId));
            if (update.getCallbackQuery().getData().equals("createDemoEvent")) {
                gService.createDemoEvent();
                sendApiMethodAsync(sendMessage("demo event was created", chatId));
            }
            if (update.getCallbackQuery().getData().equals("creatEventsForSchedule")) {
                gService.creatEventsForSchedule();
                sendApiMethodAsync(sendMessage("events from the schedule have been created", chatId));
            }
        }
    }

    private SendMessage sendMessage(String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        return sendMessage;
    }

    private SendMessage buttonManager(String message, Map<String, String> map, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        if (map != null) {
            attachButtons(sendMessage, map);
        }
        sendMessage.setChatId(chatId);
        return sendMessage;
    }

    private void attachButtons(SendMessage message, Map<String, String> map) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String s : map.keySet()) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(s);
            button.setCallbackData(s);
            keyboard.add(List.of(button));
        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }
}
