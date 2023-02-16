package com.knu.noticesender.notice.service;

import com.knu.noticesender.notice.NoticeSenderManager;
import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.model.NoticeRecord;
import com.knu.noticesender.notice.model.NoticeRecord.NoticeRecordId;
import com.knu.noticesender.notice.model.NoticeType;
import com.knu.noticesender.notice.model.Sender;
import com.knu.noticesender.notice.repository.NoticeRecordRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeRecordService {
    private final NoticeRecordRepository noticeRecordRepository;
    private final NoticeService noticeService;

    /**
     * 새로 생성되거나, 업데이트된 알림 데이터를 레코드로 추가
     * @see NoticeRecord
     */
    @Transactional
    public void generateRecord() {
        doGenerate(noticeService.findAllByType(NoticeType.NEW));
        doGenerate(noticeService.findAllByType(NoticeType.UPDATE));
    }

    private void doGenerate(List<NoticeDto> newNotices) {
        newNotices.forEach(this::doGenerate);
    }

    /**
     * 알림 데이터를 바탕으로 레코드를 생성한다
     * @param dto: 레코드에 저장할 알림 데이터
     */
    private void doGenerate(NoticeDto dto) {
        for (NoticeRecord record : NoticeRecord.createByNoticeDtoPerSender(dto)) {
            noticeRecordRepository.save(record);
            noticeService.changeType(dto.getNum(), NoticeType.OLD);
        }
    }

    /**
     * @return : 미발송 알림 레코드 불러오기
     */
    public List<NoticeRecord> findAllNotSent() {
        return noticeRecordRepository.findAllByIsSent(false);
    }

    public List<NoticeRecord> findAllBySender(Sender sender) { return noticeRecordRepository.findAllById_Sender(sender); }

    /**
     * 처리된 레코드에 대한 후처리 작업을 명시합니다
     * @param noticeRecordId: 처리된 레코드
     *
     * Ex) NoticeRecord 참조 후 처리 완료 호출
     * @see NoticeSenderManager#sendAll()
     */
    @Transactional
    public void process(NoticeRecordId noticeRecordId) {
        NoticeRecord record = noticeRecordRepository.findById(noticeRecordId)
                .orElseThrow(() -> new IllegalAccessError("Record Not Found"));
        record.process();
    }
}