package com.knu.noticesender.notice;

import com.knu.noticesender.notice.model.Sender;

/**
 * 플랫폼 별 알림을 전송하는 역할을 부여하는 인터페이스
 */
public interface SenderManager {
    void sendAll();
    void sendTo(Sender sender);
}
