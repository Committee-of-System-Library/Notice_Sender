package com.knu.noticesender.notice.repository;

import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeType;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByType(NoticeType type);

    Optional<Notice> findByNum(Long num);

    boolean existsByNum(Long num);

    default boolean notExistsByNum(Long num) {
        return !existsByNum(num);
    }
}
