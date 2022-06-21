package com.example.holopportal.telegram_bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {return "holop_itmo_bot";}

    @Override
    public String getBotToken() {return "5451762902:AAHYO-oLRHuPSNTHtcBLNYAG_PWYR_nnygE";}

    @Override
    public void onRegister() {

    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {

    }
}