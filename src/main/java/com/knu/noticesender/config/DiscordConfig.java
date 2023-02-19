package com.knu.noticesender.config;

import com.knu.noticesender.notice.model.Category;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfig {
    @Value("${discord.urls.all}")
    private String allUrl;
    @Value("${discord.urls.student}")
    private String studentUrl;
    @Value("${discord.urls.normal}")
    private String normalUrl;
    @Value("${discord.urls.scholarship}")
    private String scholarshipUrl;
    @Value("${discord.urls.sim-com}")
    private String simComUrl;
    @Value("${discord.urls.gl-sop}")
    private String glSopUrl;
    @Value("${discord.urls.graduate}")
    private String graduateUrl;
    @Value("${discord.urls.graduate-contract}")
    private String graduateContractUrl;

    @Bean
    public CategoryUrlMapper categoryURLMapper() {
        Map<Category, String> urls = new HashMap<>();
        urls.put(Category.ALL, allUrl);
        urls.put(Category.STUDENT, studentUrl);
        urls.put(Category.NORMAL, normalUrl);
        urls.put(Category.SCHOLARSHIP, scholarshipUrl);
        urls.put(Category.SIM_COM, simComUrl);
        urls.put(Category.GL_SOP, glSopUrl);
        urls.put(Category.GRADUATE_SCHOOL, graduateUrl);
        urls.put(Category.GRADUATE_CONTRACT, graduateContractUrl);

        return new CategoryUrlMapper(urls);
    }

    /**
     * Notice Category <-> Discord Webhooks-url Mapper
     */
    public static class CategoryUrlMapper {
        private final Map<Category, String> urls;

        CategoryUrlMapper(Map<Category, String> urls) {
            this.urls = urls;
        }

        public String getUrl(Category category) {
            String result = urls.get(category);
            if (result == null) {
                throw new RuntimeException("URL Not Found");
            }
            return result;
        }
    }
}
