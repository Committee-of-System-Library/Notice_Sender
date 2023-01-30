package com.knu.noticesender.notice;


import com.knu.noticesender.notice.dto.NoticeDto;

public interface NoticeSender {
    void send(NoticeDto dto);
}

