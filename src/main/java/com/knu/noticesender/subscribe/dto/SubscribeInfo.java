package com.knu.noticesender.subscribe.dto;

import com.knu.noticesender.subscribe.model.CategoryType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubscribeInfo {
    private String subId;
    private List<CategoryType> types;
}
