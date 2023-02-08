package com.knu.noticesender.notice.utils;

import com.knu.noticesender.notice.model.Convertible;
import java.util.Arrays;

public class EnumDbConvertUtils {
    public static <T extends Enum<T> & Convertible> T ofDbData(String dbData, Class<T> enumClazz) {
        return Arrays.stream(enumClazz.getEnumConstants())
                .filter(e -> e.getDbData().equals(dbData))
                .findAny()
                .orElseThrow(() -> new RuntimeException(dbData + " 가 존재하지 않음"));
    }

    public static <T extends Enum<T> & Convertible> String toDbData(T enumV) {
        return enumV.getDbData();
    }
}
