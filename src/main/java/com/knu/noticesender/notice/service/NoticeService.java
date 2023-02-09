package com.knu.noticesender.notice.service;

import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeType;
import com.knu.noticesender.notice.repository.NoticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<NoticeDto> findAllByType(NoticeType type) {
        List<Notice> result = noticeRepository.findAllByType(type);
        return NoticeDto.fromList(result);
    }
}
