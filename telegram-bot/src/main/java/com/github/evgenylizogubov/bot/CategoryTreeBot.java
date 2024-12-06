package com.github.evgenylizogubov.bot;

import com.github.evgenylizogubov.bot.command.CommandsHandler;
import com.github.evgenylizogubov.config.BotProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class CategoryTreeBot extends TelegramLongPollingBot {
    private final BotProperties botProperties;
    private final CommandsHandler commandsHandler;
    
    public CategoryTreeBot(BotProperties botProperties, CommandsHandler commandsHandler) {
        super(botProperties.getToken());
        this.botProperties = botProperties;
        this.commandsHandler = commandsHandler;
    }
    
    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }
    
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            
            if (update.getMessage().getText().startsWith("/")) {
                sendMessage(commandsHandler.handleCommands(update));
            } else {
                sendMessage(new SendMessage(chatId, "Введите команду. Чтобы получить список команд - введите /help"));
            }
        }
    }
    
    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
