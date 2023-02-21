package com.knu.noticesender.notice.repository;

import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoticeMessageRepository extends JpaRepository<NoticeMessage, Long> {
    @Query("select nm from NoticeMessage nm join fetch nm.notice where nm.isRecorded = :isRecorded")
    List<NoticeMessage> findAllByIsRecorded(@Param("isRecorded") boolean isRecorded);

    Optional<NoticeMessage> findByNotice(Notice notice);
}
