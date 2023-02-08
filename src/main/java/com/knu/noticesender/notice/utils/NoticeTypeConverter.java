package com.knu.noticesender.notice.utils;

import com.knu.noticesender.notice.model.NoticeType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NoticeTypeConverter implements AttributeConverter<NoticeType, String> {
    @Override
    public String convertToDatabaseColumn(NoticeType attribute) {
        return EnumDbConvertUtils.toDbData(attribute);
    }

    @Override
    public NoticeType convertToEntityAttribute(String dbData) {
        return EnumDbConvertUtils.ofDbData(dbData, NoticeType.class);
    }
}
