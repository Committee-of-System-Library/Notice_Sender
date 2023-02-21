package com.knu.noticesender.notice.service;

import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.dto.NoticeMessageDto;
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

    public List<NoticeDto> findAllUnrecordedNotices() {
        List<NoticeMessage> noticeMessages = noticeMessageRepository.findAllByIsRecorded(false);

        return noticeMessages.stream()
                .map(noticeMessage -> NoticeDto.ofEntity(noticeMessage.getNotice()))
                .collect(Collectors.toList());
    }

    public void setIsRecordedOfMessageTrue(NoticeDto noticeDto) {
        NoticeMessage noticeMessage = noticeMessageRepository.findByNotice(Notice.builder().id(noticeDto.getId()).build())
                .orElseThrow(() -> new RuntimeException("공지가 존재하지 않습니다"));
        noticeMessage.setIsRecorded(true);
    }
}
