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

    /**
     * 크롤링 데이터 저장 요청을 받아 공지를 저장 후 각 플랫폼 별로 발송합니다
     *
     * @param data: 저장할 공지 데이터 리스트 데이터
     */
    public void saveAndSendNotices(Result<List<NoticeSaveReqDto>> data) {
        noticeSaveService.saveOrUpdateNoticesWithMessage(data);
        noticeRecordService.generateRecord();
        noticeSenderManager.sendAll();
    }
}
