package com.knu.noticesender.config;

import com.knu.noticesender.notice.model.Category;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfig {

    //TODO("Discord Webhooks URL 추가 필요")
    @Value("${discord.urls.all}")
    private String all;

    @Bean
    public CategoryUrlMapper categoryURLMapper() {
        Map<Category, String> urls = new HashMap<>();
        urls.put(Category.ALL, all);
        return new CategoryUrlMapper(urls);
    }
}
