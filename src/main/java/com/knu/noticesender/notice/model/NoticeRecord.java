package com.knu.noticesender.notice.model;

import java.io.Serializable;
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
 * Sender 별 Notice 알림 기록을 저장하기 위한 클래스
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeRecord {
    @EmbeddedId
    private NoticeRecordId id;

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
}
