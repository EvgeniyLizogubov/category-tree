package com.github.evgenylizogubov.bot.command;

import com.github.evgenylizogubov.treeservice.dto.CategoryDto;
import com.github.evgenylizogubov.treeservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Обработчик команды добавления новой категории
 */
@Component
@RequiredArgsConstructor
public class AddElementCommand implements Command {
    private final CategoryService categoryService;
    
    @Override
    public SendMessage apply(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String[] params = update.getMessage().getText().split(" ");
        
        if (params.length < 2) {
            return new SendMessage(chatId, "Не указаны параметры операции");
        }
        
        CategoryDto result;
        String message;
        String newCategoryName = null;
        String parentCategoryName = null;
        
        if (params.length == 2) {
            newCategoryName = params[1];
        } else {
            parentCategoryName = params[1];
            newCategoryName = params[2];
        }
        
        result = categoryService.add(parentCategoryName, newCategoryName);
        
        if (result == null) {
            message = String.format("Категория \"%s\" не существует", parentCategoryName);
        } else {
            message = String.format("Новая категория \"%s\" добавлена", result.getName());
        }
        
        return new SendMessage(chatId, message);
    }
}
