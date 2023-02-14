package com.knu.noticesender.notice.model;

import com.knu.noticesender.notice.dto.NoticeDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Sender 별로 Notice 에 대한 알림 기록을 저장하기 위한 클래스
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeRecord {
    @EmbeddedId
    private NoticeRecordId id;

    /**
     * 알림 전송 여부
     */
    private Boolean isSent;

    @MapsId("noticeId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @Builder
    public NoticeRecord(NoticeRecordId id, Boolean isSent, Notice notice) {
        this.id = id;
        this.isSent = isSent;
        this.notice = notice;
    }

    /**
     * Notice - Sender 복합 키 매핑
     * @see Sender
     */
    @Data
    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class NoticeRecordId implements Serializable {
        private static final Long serialVersionUID = 1L;

        private Long noticeId;

        @Enumerated(EnumType.STRING)
        private Sender sender;

        @Builder
        public NoticeRecordId(Long noticeId, Sender sender) {
            this.noticeId = noticeId;
            this.sender = sender;
        }
    }

    /**
     * Record 후처리 동작 (Record Post Process)
     *
     * Ex) 알림 전송 후에 호출
     * @see com.knu.noticesender.notice.NoticeDiscordSender#send(NoticeDto)
     */
    public void process() {
        this.isSent = true;
    }

    /**
     * 새로운 알림 데이터 생성 시 Sender 별 레코드 생성을 위한 팩토리 메소드
     * @param dto: 신규/업데이트 알림 데이터
     * @return 신규 알림 레코드
     */
    public static List<NoticeRecord> createByNoticeDtoPerSender(NoticeDto dto) {
        List<NoticeRecord> records = new ArrayList<>();

        for (Sender sender : Sender.values()) {
            records.add(NoticeRecord.builder()
                    .id(new NoticeRecordId(dto.getNum(), sender))
                    .notice(Notice.builder().num(dto.getNum()).build())
                    .isSent(false)
                    .build());
        }
        return records;
    }
}
