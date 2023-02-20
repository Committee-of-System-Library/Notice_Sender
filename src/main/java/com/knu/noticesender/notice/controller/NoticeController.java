package com.knu.noticesender.notice.controller;

import com.knu.noticesender.core.dto.Result;
import com.knu.noticesender.notice.dto.NoticeSaveReqDto;
import com.knu.noticesender.notice.service.NoticeSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    final NoticeSaveService noticeSaveService;

    @PostMapping
    void saveOrUpdateNotices(@RequestBody @Valid Result<List<NoticeSaveReqDto>> data) {
        log.info("[공지 크롤링 요청] {}개의 요청을 처리합니다.", data.getData().size());
        noticeSaveService.saveOrUpdateNotices(data);
    }

}
