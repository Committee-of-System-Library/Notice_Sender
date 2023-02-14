package com.knu.noticesender;

import com.knu.noticesender.notice.NoticeSenderManager;
import com.knu.noticesender.notice.model.Category;
import com.knu.noticesender.notice.model.Notice;
import com.knu.noticesender.notice.model.NoticeRecord;
import com.knu.noticesender.notice.model.NoticeType;
import com.knu.noticesender.notice.model.Sender;
import com.knu.noticesender.notice.repository.NoticeRecordRepository;
import com.knu.noticesender.notice.repository.NoticeRepository;
import com.knu.noticesender.notice.service.NoticeRecordService;
import java.util.List;
import java.util.stream.IntStream;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticeRepositoryTest {

    @Autowired
    public NoticeRepository noticeRepository;

    @Autowired
    public NoticeRecordService noticeRecordService;

    @Test
    public void save() {
        IntStream.rangeClosed(1, 2).forEach(num -> {
            Notice build = Notice.builder()
                    .num((long)num)
                    .title("Title"+num)
                    .link("https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_1&wr_id=26992")
                    .category(Category.ALL)
                    .type(NoticeType.NEW)
                    .build();

            noticeRepository.save(build);
        });

        IntStream.rangeClosed(3, 4).forEach(num -> {
            Notice build = Notice.builder()
                    .num((long)num)
                    .title("Title"+num)
                    .link("https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_1&wr_id=26992")
                    .category(Category.SIM_COM)
                    .type(NoticeType.UPDATE)
                    .build();

            noticeRepository.save(build);
        });

        IntStream.rangeClosed(5, 6).forEach(num -> {
            Notice build = Notice.builder()
                    .num((long)num)
                    .title("Title"+num)
                    .link("https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_1&wr_id=26992")
                    .category(Category.GL_SOP)
                    .type(NoticeType.OLD)
                    .build();

            noticeRepository.save(build);
        });
    }

    @Test
    public void findByType() {
        System.out.println(noticeRepository.findAllByType(NoticeType.UPDATE).size());
    }

    @Test
    public void generateRecord() {
        noticeRecordService.generateRecord();
    }


    @Autowired
    public NoticeSenderManager sender;

    @Test
    public void generateRecord2() {
        sender.sendAll();
    }

    @Test
    public void notice() {

    }

    @Autowired
    public NoticeRecordRepository noticeRecordRepository;
    @Test
    public void findAllBySender() {
        System.out.println(noticeRecordRepository.findAllByIdSender(Sender.DISCORD).size());
        System.out.println(noticeRecordRepository.findAllById_Sender(Sender.DISCORD).size());
    }
}
