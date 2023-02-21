package com.knu.noticesender.notice.model;

import com.knu.noticesender.core.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "NOTICE_MESSAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    @Column(name = "is_recorded")
    private boolean isRecorded;

    @Builder
    public NoticeMessage(Long id, Notice notice, boolean isRecorded) {
        this.id = id;
        this.notice = notice;
        this.isRecorded = isRecorded;
    }

    public NoticeMessage(Notice notice) {
        this.notice = notice;
    }
}
