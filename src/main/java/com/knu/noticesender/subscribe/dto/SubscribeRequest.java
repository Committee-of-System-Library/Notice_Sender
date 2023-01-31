package com.knu.noticesender.subscribe.dto;

import com.knu.noticesender.subscribe.model.SubscribeType;
import java.util.List;
import lombok.Data;

@Data
public class SubscribeRequest {
    private String subId;
    private List<SubscribeType> categories;
}