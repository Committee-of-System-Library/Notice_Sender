package com.knu.noticesender.notice.api;

import com.knu.noticesender.notice.NoticeMailSender;
import com.knu.noticesender.notice.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeMailSenderApi {

    private final NoticeMailSender noticeMailSender;

    @PostMapping("/api/v1/notice-sender/mail/send")
    public void sendNotice(NoticeDto dto) {
//        noticeMailSender.send(dto);
    }
}

