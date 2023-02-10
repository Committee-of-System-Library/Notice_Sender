package com.knu.noticesender.notice.repository;

import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByType(NoticeType type);
}
