package com.knu.noticesender.notice.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {
    @Id
    private Long num;

    private String link;

    private String title;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String content;

    private LocalDateTime createdDate;

    @Builder
    public Notice(Long num, String link, String title, Category category, String content, LocalDateTime createdDate) {
        this.num = num;
        this.link = link;
        this.title = title;
        this.category = category;
        this.content = content;
        this.createdDate = createdDate;
    }
}
