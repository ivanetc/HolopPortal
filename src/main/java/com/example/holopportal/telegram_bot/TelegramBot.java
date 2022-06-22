package com.example.holopportal.telegram_bot;

import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

public class TelegramBot extends TelegramLongPollingBot {

    private static TelegramBot telegramBot;

    public static TelegramBot getTelegramBot() {
        if (telegramBot == null) {
            telegramBot = new TelegramBot();
        }

        return telegramBot;
    }

    private UserService userService;

    private TelegramBot() {
        super();
    }

    @Override
    public String getBotUsername() {
        return "holop_itmo_bot";
    }

    @Override
    public String getBotToken() {
        return "5451762902:AAHYO-oLRHuPSNTHtcBLNYAG_PWYR_nnygE";
    }

    @Override
    public void onRegister() {

    }


    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        for (Update update : updates) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("/start")) {
                    processStartCommand(update);
                }
            }
        }
    }

    private void processStartCommand(Update update) {
        String telegramLogin = update.getMessage().getFrom().getUserName();
        Optional<User> currentUserOptional = userService.findByTelegramLogin(telegramLogin);
        SendMessage message = new SendMessage();
        String currentChatId = update.getMessage().getChatId().toString();
        message.setChatId(currentChatId);

        if (currentUserOptional.isPresent()) {
            message.setText("Привет:) добро пожаловать в систему Holop");
            userService.setUserChatId(currentUserOptional.get().getId(), currentChatId);
        } else {
            message.setText("Вы не найдены в системе, обратитесь к администратору!");
        }
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public boolean sentNotification(User portalUser, String messageText) {
        // добавить миграцию в бд где добавить колонку chatId и
        // добавляем поля в User
        // добавить метод в репозиторий
        // findByTelegramLogin(string) где TelegramLogin == telegram_login в базу
        // Добавляем метод в сервис
        // дернем сервис в onUpdatesReceived


        // здесь нужно вместо 200622296 подставить user.chatId
        return sentNotification(portalUser.getChatId(), messageText);
        // добавить TelegramBotUtill
    }

    boolean sentNotification(String chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message); // Call method to send the message
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
