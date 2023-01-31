package com.knu.noticesender.subscribe.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.knu.noticesender.subscribe.dto.SubscribeInfo;
import com.knu.noticesender.subscribe.dto.SubscribeRequest;
import com.knu.noticesender.subscribe.service.SubscribeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscribeApi {
    private final SubscribeService subscribeService;

    @PutMapping("/api/v1/subscribe")
    public void subscribe(@RequestBody SubscribeRequest req) {
        subscribeService.subscribe(req);
    }

    @DeleteMapping("/api/v1/unsubscribe")
    public void unsubscribe(@RequestBody SubscribeRequest req) {
        subscribeService.unsubscribe(req);
    }

    @GetMapping("/api/v1/subscribe")
    public List<SubscribeInfo> getSubscribeInfo(@RequestParam boolean all, @RequestParam(required = false) String id) {
        if (all) return subscribeService.findAll();
        return List.of(subscribeService.findById(id));
    }

}
