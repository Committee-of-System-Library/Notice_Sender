package com.knu.noticesender.notice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.knu.noticesender.notice.model.Category;
import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeType;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class NoticeSaveReqDto {

    @NotNull
    private Long num;

    @NotNull
    private Category category;

    @NotNull
    private String link;

    @NotNull
    private String title;

    private String content;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdDate;

    public static Notice toEntity(NoticeSaveReqDto dto) {
        return Notice.builder()
                .num(dto.getNum())
                .category(dto.getCategory())
                .type(NoticeType.NEW)
                .link(dto.getLink())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdDate(dto.getCreatedDate())
                .build();
    }

    public boolean isDifferentWith(Notice notice) {
        return !Objects.equals(notice.getTitle(), this.title)
                || !Objects.equals(notice.getCategory(), this.category)
                || !Objects.equals(notice.getContent(), this.content);
    }
}
