package com.knu.noticesender.config;

import com.knu.noticesender.notice.NoticeDiscordSender;
import com.knu.noticesender.notice.NoticeSender;
import com.knu.noticesender.notice.model.Sender;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {

    @Bean
    public NoticeSenderMapper noticeSenderMapper(
            NoticeDiscordSender discordSender) {
        Map<Sender, NoticeSender> noticeSenderInfo = new HashMap<>();
        noticeSenderInfo.put(Sender.DISCORD, discordSender);
        return new NoticeSenderMapper(noticeSenderInfo);
    }


    /**
     * Sender - NoticeSender 쌍 저장소
     */
    public static class NoticeSenderMapper {
        private final Map<Sender, NoticeSender> noticeSenderMapper;

        NoticeSenderMapper(Map<Sender, NoticeSender> noticeSenderMapper) {
            this.noticeSenderMapper = noticeSenderMapper;
        }

        public NoticeSender getNoticeSender(Sender sender) {
            return noticeSenderMapper.get(sender);
        }
    }
}
