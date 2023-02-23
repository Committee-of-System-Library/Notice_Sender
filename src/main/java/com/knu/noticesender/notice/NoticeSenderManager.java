package com.knu.noticesender.notice;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.knu.noticesender.notice.model.Sender;
import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.model.NoticeRecord;
import com.knu.noticesender.notice.service.NoticeRecordService;
import org.springframework.web.client.HttpClientErrorException;
import com.knu.noticesender.config.SenderConfig.NoticeSenderMapper;

/**
 * NoticeRecord 데이터 참조, NoticeSender 를 통해 알림 전송
 * Notice Sender -> NoticeSender 매핑 활용
 * @see NoticeSender
 * @see NoticeSenderMapper
 * @see com.knu.noticesender.notice.model.Sender
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeSenderManager implements SenderManager {
    private final NoticeRecordService noticeRecordService;
    private final NoticeSenderMapper noticeSenderMapper;

    /**
     * 미발송 알림을 모두 전송합니다.
     */
    public void sendAll() {
        doSend(noticeRecordService.findAllNotSent());
    }

    /**
     * 알림 플랫폼 별 미발송 알림 전송
     * @param sender: 알림 플랫폼
     */
    @Override
    public void sendTo(Sender sender) {
        doSend(noticeRecordService.findAllBySender(sender));
    }

    /**
     * NoticeRecord 의 Sender, Notice 정보를 참조해 알림 전송
     * @param record: 알림 전송 참조 레코드
     * @see NoticeRecord
     */
    private void doSend(NoticeRecord record) {
        Sender sender = record.getId().getSender();
        Long noticeId = record.getId().getNoticeId();
        NoticeDto dto = NoticeDto.ofEntity(record.getNotice());
        NoticeSender noticeSender = noticeSenderMapper.getNoticeSender(sender);
        try {
            noticeSender.send(dto);
            postSend(record);
        } catch (HttpClientErrorException e) {
            log.error(String.format("Sender[%s] Notice[%d] 발송 실패", sender, noticeId), e);
        } catch (Exception e) {
            log.error(String.format("Sender[%s] Notice[%d] 저장 실패", sender, noticeId), e);
        }
    }

    private void doSend(List<NoticeRecord> records) {
        records.forEach(this::doSend);
    }

    /**
     * 알림 발송 후처리 작업
     */
    private void postSend(NoticeRecord record) {
        noticeRecordService.process(record.getId());
    }
}
