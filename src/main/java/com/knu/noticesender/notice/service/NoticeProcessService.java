package com.knu.noticesender.notice.service;

import com.knu.noticesender.core.dto.Result;
import com.knu.noticesender.notice.NoticeSenderManager;
import com.knu.noticesender.notice.dto.NoticeSaveReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeProcessService {
    private final NoticeRecordService noticeRecordService;
    private final NoticeSaveService noticeSaveService;
    private final NoticeSenderManager noticeSenderManager;

    @Transactional
    public void saveAndSendNotices(Result<List<NoticeSaveReqDto>> data) {
        noticeSaveService.saveOrUpdateNotices(data);
        noticeRecordService.generateRecord();
        noticeSenderManager.sendAll();
    }
}
