package com.github.evgenylizogubov.bot.command;

import com.github.evgenylizogubov.treeservice.dto.CategoryDto;
import com.github.evgenylizogubov.treeservice.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = {
        AddElementCommand.class
})
public class AddElementCommandTest {
    @MockitoBean
    public CategoryService categoryService;
    
    @Autowired
    private AddElementCommand addElementCommand;
    
    @Test
    public void apply() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("AddedCategory");
        categoryDto.setLevel(1);
        
        when(categoryService.add(any(), any())).thenReturn(categoryDto);
        
        Chat chat = new Chat();
        chat.setId(1L);
        User user = new User();
        user.setId(1L);
        
        Message message = new Message();
        message.setText("/addElement " + categoryDto.getName());
        message.setChat(chat);
        message.setFrom(user);
        
        Update update = new Update();
        update.setMessage(message);
        
        SendMessage sendMessage = addElementCommand.apply(update);
        assertEquals("Новая категория \"" + categoryDto.getName() + "\" добавлена", sendMessage.getText());
    }
}
