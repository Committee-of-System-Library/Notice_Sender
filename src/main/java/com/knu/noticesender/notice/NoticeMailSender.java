package com.knu.noticesender.notice;

import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.subscribe.model.Subscribe;
import com.knu.noticesender.subscribe.repository.SubscribeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NoticeMailSender implements NoticeSender{
    private final JavaMailSender mailSender;
    private final SubscribeRepository subscribeRepository;

    @Value("${group.email}")
    private String SERVER_EMAIL;

    @Override
    public void send(NoticeDto dto) {
        SimpleMailMessage msg = createNoticeMessage(dto);
        mailSender.send(msg);
    }

    private SimpleMailMessage createNoticeMessage(NoticeDto dto) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(SERVER_EMAIL);
        msg.setTo(findAllSubscribers());
        msg.setSubject(dto.getTitle());
        msg.setText(dto.getText());

        return msg;
    }

    private String[] findAllSubscribers() {
        List<Subscribe> subscribers = subscribeRepository.findAll();
        return subscribers.stream().map(Subscribe::getId).toArray(String[]::new);
    }
}
