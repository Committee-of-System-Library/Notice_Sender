package com.knu.noticesender.notice.dto;

import com.knu.noticesender.notice.model.Category;
import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeType;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
public class NoticeSaveReqDto {

    @NotNull
    private Long num;
    @NotNull
    private Category category;
    @NotNull
    private NoticeType type;
    @NotNull
    private String link;
    @NotNull
    private String title;
    private String content;
    @NotNull
    private LocalDateTime createdDate;

    public static Notice toEntity(NoticeSaveReqDto dto) {
        return Notice.builder()
                .num(dto.getNum())
                .category(dto.getCategory())
                .type(dto.getType())
                .link(dto.getLink())
                .title(dto.getTitle())
                        .content(dto.getContent())
                .createdDate(dto.getCreatedDate())
                .build();
    }
}
