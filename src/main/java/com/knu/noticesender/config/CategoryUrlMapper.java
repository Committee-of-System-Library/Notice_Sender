package com.knu.noticesender.config;

import com.knu.noticesender.notice.model.Category;
import java.util.Map;

public class CategoryUrlMapper {
    private final Map<Category, String> urls;

    CategoryUrlMapper(Map<Category, String> urls) {
        this.urls = urls;
    }

    public String getUrl(Category category) {
        return urls.get(category);
    }
}