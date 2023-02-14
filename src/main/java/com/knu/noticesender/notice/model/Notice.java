package com.knu.noticesender.notice.model;

import com.knu.noticesender.notice.utils.CategoryConverter;
import com.knu.noticesender.notice.utils.NoticeTypeConverter;
import java.time.LocalDateTime;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 서버 - 데이터베이스 간 공유하는 알림 데이터 클래스
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
    @Id
    private Long num;

    private String link;

    private String title;

    private String content;

    private LocalDateTime createdDate;

    @Convert(converter = CategoryConverter.class)
    private Category category;

    @Convert(converter = NoticeTypeConverter.class)
    private NoticeType type;

    @Builder
    public Notice(Long num, String link, String title, Category category,
                  String content, LocalDateTime createdDate, NoticeType type) {
        this.num = num;
        this.link = link;
        this.title = title;
        this.category = category;
        this.content = content;
        this.createdDate = createdDate;
        this.type = type;
    }

    public void changeType(NoticeType type) {
        this.type = type;
    }
}
