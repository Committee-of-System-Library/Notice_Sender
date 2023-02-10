package com.knu.noticesender.notice.dto;

import com.knu.noticesender.notice.model.Category;
import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class NoticeDto {
    @NotNull
    private Long num;
    @NotNull
    private Category category;
    @NotNull
    private NoticeType type;
    private String link;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public NoticeDto(Long num, Category category, NoticeType type, String link, String title, String content,
                     LocalDateTime createdDate) {
        this.num = num;
        this.category = category;
        this.type = type;
        this.link = link;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    private NoticeDto(Notice notice) {
        this.num = notice.getNum();
        this.category = notice.getCategory();
        this.type = notice.getType();
        this.link = notice.getLink();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdDate = notice.getCreatedDate();
    }

    public static List<NoticeDto> fromList(List<Notice> notices) {
        return notices.stream().map(NoticeDto::new).collect(Collectors.toList());
    }

    public static NoticeDto ofEntity(Notice notice) {
        return new NoticeDto(notice);
    }
}