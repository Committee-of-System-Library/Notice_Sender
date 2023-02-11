package com.knu.noticesender.notice.repository;

import com.knu.noticesender.notice.model.NoticeRecord;
import com.knu.noticesender.notice.model.NoticeRecord.NoticeRecordId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRecordRepository extends JpaRepository<NoticeRecord, NoticeRecordId> {
    /**
     * 발송 여부를 바탕으로 레코드를 디비에서 조회
     * 알림 전송에 사용될 데이터로 Notice 를 대부분의 상황에서 사용하기 때문에 fetch join
     * @param isSent : true: 발송, false: 미발송
     */
    @Query("select nr from NoticeRecord nr join fetch nr.notice where nr.isSent = :isSent")
    List<NoticeRecord> findAllByIsSent(@Param("isSent")boolean isSent);
}
