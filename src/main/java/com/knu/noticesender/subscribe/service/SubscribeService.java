package com.knu.noticesender.subscribe.service;

import com.knu.noticesender.subscribe.dto.SubscribeInfo;
import com.knu.noticesender.subscribe.dto.SubscribeRequest;
import com.knu.noticesender.subscribe.model.SubscribeType;
import java.util.List;

public interface SubscribeService {
    void subscribe(SubscribeRequest req);
    void unsubscribe(SubscribeRequest req);

    List<SubscribeInfo> findAll();

    SubscribeInfo findById(String subId);

    List<String> findAllSubIdByType(SubscribeType type);
}
