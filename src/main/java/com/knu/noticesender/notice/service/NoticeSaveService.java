package com.knu.noticesender.notice.service;

import com.knu.noticesender.core.dto.Result;
import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.dto.NoticeSaveReqDto;
import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeType;
import com.knu.noticesender.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeSaveService {

    private final NoticeRepository noticeRepository;
    private final NoticeRecordService noticeRecordService;

    /**
     * 공지사항 크롤링 데이터 저장 요청을 받아,
     * 저장 또는 변경사항이 있을 시 업데이트가 수행됩니다
     *
     * @param data: 공지사항 크롤링 데이터 리스트를 감싼 객체
     * @see NoticeSaveReqDto
     */
    @Transactional
    public void saveOrUpdateNotices(Result<List<NoticeSaveReqDto>> data) {
        saveNoticesIfNotExists(data);
        updateNoticesWithCondition(data);
        noticeRecordService.generateRecord();

    }

    private void updateNoticesWithCondition(Result<List<NoticeSaveReqDto>> data) {
        data.getData().stream()
                .filter(dto -> noticeRepository.existsByNum(dto.getNum()))
                .filter(this::isUpdatedNotice)
                .peek(dto -> log.info("[공지 크롤링 요청] 공지 업데이트 num: {}, category: {}", dto.getNum(), dto.getCategory()))
                .forEach(this::updateNotice);
    }

    private void saveNoticesIfNotExists(Result<List<NoticeSaveReqDto>> data) {
        List<Notice> notices = data.getData().stream()
                .filter(dto -> noticeRepository.notExistsByNum(dto.getNum()))
                .map(NoticeSaveReqDto::toEntity)
                .collect(Collectors.toList());
        log.info("[공지 크롤링 요청] {} 개의 공지를 저장합니다.", notices.size());
        noticeRepository.saveAll(notices);
    }

    private boolean isUpdatedNotice(NoticeSaveReqDto dto) {
        Notice notice = noticeRepository.findByNum(dto.getNum()).orElseThrow(RuntimeException::new);

        return !Objects.equals(notice.getTitle(), dto.getTitle())
                || !Objects.equals(notice.getCategory(), dto.getCategory())
                || !Objects.equals(notice.getContent(), dto.getContent());
    }

    private void updateNotice(NoticeSaveReqDto dto) {
        Notice notice = noticeRepository.findByNum(dto.getNum()).orElseThrow(RuntimeException::new);
        notice.setUpdatedData(dto.getTitle(), dto.getContent(), dto.getCategory());
    }
}
