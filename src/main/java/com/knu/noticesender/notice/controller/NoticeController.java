package com.knu.noticesender.notice.controller;

import com.knu.noticesender.core.dto.Result;
import com.knu.noticesender.notice.dto.NoticeSaveReqDto;
import com.knu.noticesender.notice.service.NoticeProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeProcessService noticeProcessService;

    @PostMapping
    void saveOrUpdateNotices(@RequestBody @Valid Result<List<NoticeSaveReqDto>> data) {
        log.info("[공지 크롤링 요청] {}개의 요청을 처리합니다.", data.getData().size());
        noticeProcessService.saveAndSendNotices(data);
    }
}
