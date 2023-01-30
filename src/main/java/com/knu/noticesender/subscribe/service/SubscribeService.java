package com.knu.noticesender.subscribe.service;

import com.knu.noticesender.subscribe.dto.SubscribeInfo;
import com.knu.noticesender.subscribe.dto.SubscribeRequest;
import java.util.List;

public interface SubscribeService {
    void subscribe(SubscribeRequest req);
    void unsubscribe(SubscribeRequest req);

    List<SubscribeInfo> getAllSubscribeInfo();

    SubscribeInfo getSubscribeInfo(String subId);
}
