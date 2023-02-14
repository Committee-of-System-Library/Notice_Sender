package com.knu.noticesender.notice.api;

import com.knu.noticesender.notice.NoticeSenderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice-sender/main")
public class MainNoticeSenderApi {
    private final NoticeSenderManager sender;

    @PostMapping("/send-all")
    public void sendAll() {
        sender.sendAll();
//        sender.sendUpdateNotice();
    }
}
