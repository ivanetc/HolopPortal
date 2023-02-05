package com.example.holopportal.telegram_bot;

import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBotUtil {

    TelegramBot telegramBot;

    @Value("${bot.enabled:false}")
    boolean isEnabled;

    @Autowired
    TelegramBotUtil(UserService userService) {
        if (isEnabled) {
            this.telegramBot = TelegramBot.getTelegramBot();
            this.telegramBot.setUserService(userService);

            try {
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                botsApi.registerBot(telegramBot);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean sentNotification(User user, String message) {
        // добавить миграцию в бд где добавить колонку chatId и
        // здесь нужно вместо 200622296 подставить user.chatId
        if (isEnabled) {
            return telegramBot.sentNotification(user.getChatId(), message);
        }
        return false;
    }

}
