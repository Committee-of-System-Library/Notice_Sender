package com.knu.noticesender.notice;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.knu.noticesender.notice.dto.NoticeDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Value;
import com.knu.noticesender.subscribe.model.SubscribeType;
import com.knu.noticesender.subscribe.service.SubscribeService;

@Component
@RequiredArgsConstructor
public class NoticeMailSender implements NoticeSender{
    private final JavaMailSender mailSender;
    private final SubscribeService subscribeService;

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
//        msg.setText(dto.getText());

        return msg;
    }

    private String[] findAllSubscribers() {
        List<String> subscribers = subscribeService.findAllSubIdByType(SubscribeType.EMAIL);
        return subscribers.toArray(String[]::new);
    }
}
