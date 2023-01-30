package com.knu.noticesender.subscribe.dto;

import com.knu.noticesender.subscribe.model.CategoryType;
import java.util.List;
import lombok.Data;

@Data
public class SubscribeRequest {
    private String subId;
    private List<CategoryType> categories;
}