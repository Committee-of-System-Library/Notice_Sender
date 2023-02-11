package com.knu.noticesender.notice;


import com.knu.noticesender.notice.dto.NoticeDto;

/**
 * 알림 데이터를 통해 알림 전송 역할을 부여하는 인터페이스
 */
public interface NoticeSender {
    void send(NoticeDto dto);
}

