package com.knu.noticesender.notice.service;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeType;
import com.knu.noticesender.notice.repository.NoticeRepository;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    /**
     * 공지사항 알림 상태 별 공지사항 데이터를 불러옵니다
     *
     * @param type: 공지사항 알림 상태
     * @see NoticeType
     * @see NoticeDto
     * @return NoticeDto 로 변환된 Notice 데이터
     */
    public List<NoticeDto> findAllByType(NoticeType type) {
        List<Notice> result = noticeRepository.findAllByType(type);
        return NoticeDto.fromList(result);
    }

    /**
     * 공지사항 데이터를 조회한 후의 동작을 명시합니다
     *
     * 1. 알림 상태를 변경합니다 NEW/UPDATE -> OLD
     */
    @Transactional
    public void postFindNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wrong Id"));
        notice.changeType(NoticeType.OLD);
    }
}
