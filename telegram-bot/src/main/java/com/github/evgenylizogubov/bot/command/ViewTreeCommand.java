package com.github.evgenylizogubov.bot.command;

import com.github.evgenylizogubov.treeservice.dto.CategoryDto;
import com.github.evgenylizogubov.treeservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ViewTreeCommand implements Command {
    private final CategoryService categoryService;
    
    @Override
    public SendMessage apply(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        List<CategoryDto> categories = categoryService.getAll();
        String message;
        
        if (categories.isEmpty()) {
            message = "Ни одной категории не добавлено";
        } else {
            message = generateTreeView(categories);
        }
        
        return new SendMessage(chatId, message);
    }
    
    private String generateTreeView(List<CategoryDto> categories) {
        StringBuilder stringBuilder = new StringBuilder();
        String indentSign = "--";
        
        categories.forEach(category -> stringBuilder
                .append(indentSign.repeat(category.getLevel()))
                .append(category.getName())
                .append("\n"));
        
        return stringBuilder.toString();
    }
}
