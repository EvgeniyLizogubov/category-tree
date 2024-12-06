package com.github.evgenylizogubov.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("bot")
@Configuration
@Data
public class BotProperties {
    private String name;
    private String token;
}
