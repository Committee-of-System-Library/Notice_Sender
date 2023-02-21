package com.knu.noticesender.notice.repository;

import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeMessageRepository extends JpaRepository<NoticeMessage, Long> {
}
