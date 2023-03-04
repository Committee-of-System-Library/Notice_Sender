package com.knu.noticesender.notice.dto;

import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeMessage;
import com.knu.noticesender.notice.model.NoticeType;
import lombok.Builder;
import lombok.Data;

@Data
public class NoticeMessageDto {

    private Long id;
    private NoticeDto notice;
    private boolean isRecorded;
    private NoticeType noticeType;

    @Builder
    public NoticeMessageDto(Long id, NoticeDto notice, boolean isRecorded, NoticeType noticeType) {
        this.id = id;
        this.notice = notice;
        this.isRecorded = isRecorded;
        this.noticeType = noticeType;
    }

    public static NoticeMessageDto fromEntity(NoticeMessage noticeMessage) {

        return NoticeMessageDto.builder()
                .id(noticeMessage.getId())
                .notice(NoticeDto.ofEntity(noticeMessage.getNotice()))
                .isRecorded(noticeMessage.isRecorded())
                .noticeType(noticeMessage.getNoticeType())
                .build();
    }
}
