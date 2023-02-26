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

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
    @Comment("공지가 실제 생성된 날짜")
    private LocalDateTime createdDate;

    @Column(name = "saved_at")
    @Comment("DB에 공지가 저장된 시각")
    @CreatedDate
    private LocalDateTime savedAt;

    @Column(name = "updated_at")
    @Comment("DB에 공지가 업데이트 된 시각")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

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

    public void setUpdatedData(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.type = NoticeType.UPDATE;
    }

    public void changeType(NoticeType type) {
        this.type = type;
    }
}
