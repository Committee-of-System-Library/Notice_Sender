package com.knu.noticesender.notice.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeType;
import com.knu.noticesender.notice.repository.NoticeRepository;
import org.springframework.transaction.annotation.Transactional;

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
     * 공지사항 알림 상태를 변경합니다
     */
    @Transactional
    public void changeType(Long id, NoticeType type) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wrong Id"));
        notice.changeType(type);
    }
}
