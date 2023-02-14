package com.knu.noticesender.notice.api;

import com.knu.noticesender.notice.NoticeDiscordSender;
import com.knu.noticesender.notice.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeDiscordSenderApi {

    private final NoticeDiscordSender sender;

    @PostMapping("/api/v1/notice-sender/discord/send")
    public void sendNotice(NoticeDto dto) {
        sender.send(dto);
    }
}
