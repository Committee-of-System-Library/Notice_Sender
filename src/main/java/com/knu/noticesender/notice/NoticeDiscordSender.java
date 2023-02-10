package com.knu.noticesender.notice;

import com.knu.noticesender.notice.dto.discord.DiscordMessage;
import com.knu.noticesender.notice.utils.NoticeDiscordMessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.knu.noticesender.notice.dto.NoticeDto;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knu.noticesender.config.CategoryUrlMapper;

@Service
@RequiredArgsConstructor
public class NoticeDiscordSender implements NoticeSender{

    private final ObjectMapper objectMapper;
    private final CategoryUrlMapper categoryUrlMapper;

    @Override
    public void send(NoticeDto dto) {
        RestTemplate rest = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(createSendBody(dto), createSendHeaders());
        rest.postForEntity(categoryUrlMapper.getUrl(dto.getCategory()), request, Object.class);
    }

    private HttpHeaders createSendHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String createSendBody(NoticeDto dto) {
        DiscordMessage discordMessage = NoticeDiscordMessageConverter.convertToDiscordMessage("Server", dto);
        return convertToJson(discordMessage.getMessage());
    }

    private String convertToJson(Object body) {
        try {
            return objectMapper.writeValueAsString(body);
        } catch (Exception e) {
            //TODO("Error Code 수정")
            throw new RuntimeException("데이터 변환 오류");
        }
    }
}