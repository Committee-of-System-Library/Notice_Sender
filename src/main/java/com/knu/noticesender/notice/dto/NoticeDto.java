package com.knu.noticesender.notice.dto;

import com.knu.noticesender.notice.model.Category;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class NoticeDto {

    private Long num;

    private String link;

    private String title;

    private Category category;

    private String content;

    private LocalDateTime createdDate;
}