package com.knu.noticesender.notice.service;

import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeMessage;
import com.knu.noticesender.notice.repository.NoticeMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeMessageService {

    private final NoticeMessageRepository noticeMessageRepository;

    /**
     * record 테이블에 저장되지 않은 모든 notice를 가져온다
     *
     * @return Notice dto 리스트 - notice 데이터이나 읽기 전용임
     */
    public List<NoticeDto> findAllUnrecordedNotices() {
        List<NoticeMessage> noticeMessages = noticeMessageRepository.findAllByIsRecorded(false);

        return noticeMessages.stream()
                .map(noticeMessage -> NoticeDto.ofEntity(noticeMessage.getNotice()))
                .collect(Collectors.toList());
    }

    /**
     * 레코드에 저장 후에 메세지의 isRecorded 필드를 True로 만든다
     *
     * @param noticeDto notice 읽기 전용 데이터
     */

    public void setIsRecordedOfMessageTrue(NoticeDto noticeDto) {
        NoticeMessage noticeMessage = noticeMessageRepository.findByNotice(Notice.createNoticeFromId(noticeDto.getId()))
                .orElseThrow(() -> new RuntimeException("공지가 존재하지 않습니다"));
        noticeMessage.setIsRecorded(true);
    }
}
