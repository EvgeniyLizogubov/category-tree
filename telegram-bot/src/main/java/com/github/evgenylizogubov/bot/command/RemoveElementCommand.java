package com.github.evgenylizogubov.bot.command;

import com.github.evgenylizogubov.treeservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Обработчик команды удаления категории
 */
@Component
@RequiredArgsConstructor
public class RemoveElementCommand implements Command {
    private final CategoryService categoryService;
    
    @Override
    public SendMessage apply(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String[] params = update.getMessage().getText().split(" ");
        
        if (params.length < 2) {
            return new SendMessage(chatId, "Не указаны параметры операции");
        }
        
        String categoryName = params[1];
        int deleted = categoryService.delete(categoryName);
        String message;
        
        if (deleted == 0) {
            message = String.format("Категория \"%s\" не существует", categoryName);
        } else {
            message = String.format("Категория \"%s\" была удалена", categoryName);
        }
        
        return new SendMessage(chatId, message);
    }
}
