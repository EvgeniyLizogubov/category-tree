package com.github.evgenylizogubov.bot.command;

import com.github.evgenylizogubov.util.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
public class CommandsHandler {
    private final Map<String, Command> commands;
    
    public CommandsHandler(
            @Autowired ViewTreeCommand viewTreeCommand,
            @Autowired AddElementCommand addElementCommand,
            @Autowired RemoveElementCommand removeElementCommand,
            @Autowired HelpCommand helpCommand
    ) {
        this.commands = Map.of(
                Consts.VIEW_TREE_COMMAND, viewTreeCommand,
                Consts.ADD_ELEMENT_COMMAND, addElementCommand,
                Consts.REMOVE_ELEMENTS_COMMAND, removeElementCommand,
                Consts.HELP_COMMAND, helpCommand
        );
    }
    
    public SendMessage handleCommands(Update update) {
        String messageText = update.getMessage().getText();
        String command = messageText.split(" ")[0];
        String chatId = update.getMessage().getChatId().toString();
        
        Command commandHandler = commands.get(command);
        if (commandHandler == null) {
            return new SendMessage(chatId, "Команда не найдена. Чтобы получить список команд - введите /help");
        } else {
            return commandHandler.apply(update);
        }
        
    }
}
