package com.knu.noticesender.notice.dto;

import com.knu.noticesender.notice.model.Category;
import com.knu.noticesender.notice.model.NoticeType;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

    public String convertToDiscordMessage() {
        //TODO("디스코드 메시지 양식")
        switch (this.type) {
            case NEW: break;
            case UPDATE: break;
            case OLD: break;
        }
        return null;
    }
}