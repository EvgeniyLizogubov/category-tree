package com.github.evgenylizogubov.bot.command;

import com.github.evgenylizogubov.util.Consts;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Обработчик команды вывода доступных команд
 */
@Component
public class HelpCommand implements Command {
    @Override
    public SendMessage apply(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String message = "Список доступных команд:\n" +
                Consts.VIEW_TREE_COMMAND + " - просмотр дерева категорий\n" +
                Consts.ADD_ELEMENT_COMMAND + " Наименование_новой_категории" + " - добавление новой корневой категории\n" +
                Consts.ADD_ELEMENT_COMMAND + " Наименование_родительской_категории Наименование_новой_категории" + " - добавление дочерней категории к существующей\n" +
                Consts.REMOVE_ELEMENTS_COMMAND + " - удаление категории и всех её дочерних категорий\n";
        
        return new SendMessage(chatId, message);
    }
}
