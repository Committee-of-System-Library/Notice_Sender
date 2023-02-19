package com.knu.noticesender.notice.model;

import com.knu.noticesender.notice.utils.CategoryConverter;
import com.knu.noticesender.notice.utils.NoticeTypeConverter;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 서버 - 데이터베이스 간 공유하는 알림 데이터 클래스
 */
@Entity
@Getter
@Table(name = "NOTICE_TABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long num;

    private String link;

    private String title;

    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdDate;

    @Convert(converter = CategoryConverter.class)
    private Category category;

    @Column(name = "status")
    @Convert(converter = NoticeTypeConverter.class)
    private NoticeType type;

    @Builder
    public Notice(Long id, Long num, String link, String title, String content, LocalDateTime createdDate,
                  Category category, NoticeType type) {
        this.id = id;
        this.num = num;
        this.link = link;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.category = category;
        this.type = type;
    }

    public void changeType(NoticeType type) {
        this.type = type;
    }
}
