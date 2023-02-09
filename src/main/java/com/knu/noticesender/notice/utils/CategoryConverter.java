package com.knu.noticesender.notice.utils;

import com.knu.noticesender.notice.model.Category;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<Category, String> {
    @Override
    public String convertToDatabaseColumn(Category attribute) {
        return EnumDbConvertUtils.toDbData(attribute);
    }

    @Override
    public Category convertToEntityAttribute(String dbData) {
        return EnumDbConvertUtils.ofDbData(dbData, Category.class);
    }
}
