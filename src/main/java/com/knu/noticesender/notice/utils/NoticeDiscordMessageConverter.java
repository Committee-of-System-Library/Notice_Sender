package com.knu.noticesender.notice.utils;

import java.util.List;
import java.util.ArrayList;
import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.dto.discord.DiscordMessage;
import com.knu.noticesender.notice.dto.discord.DiscordMessage.Embed;
import com.knu.noticesender.notice.dto.discord.DiscordMessage.Embed.Field;
import com.knu.noticesender.notice.dto.discord.DiscordMessage.Footer;
import java.time.format.DateTimeFormatter;


/**
 * Notice 기반 DiscordMessage 를 생성하는 클래스
 * @see DiscordMessage
 */
public class NoticeDiscordMessageConverter {
    public static DiscordMessage convertToDiscordMessage(String botName, NoticeDto dto) {
        DiscordMessage message = new DiscordMessage();
        message.setUsername(botName);
        message.setContent("\uD83D\uDCE2 " + dto.getType() + "\n" + createTitle(dto));
        message.setEmbeds(createEmbedMessages(dto));
        return message;
    }

    private static List<Embed> createEmbedMessages(NoticeDto dto) {
        List<Embed> embeds = new ArrayList<>();
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("\u200B", dto.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"))));

        Embed embed = Embed.builder()
                .title(createTitle(dto))
                .url(dto.getLink())
                .description("")
                .fields(fields)
                .footer(new Footer("#시스템도서위원회"))
                .build();
        embeds.add(embed);
        return embeds;
    }

    private static String createTitle(NoticeDto dto) {
        return String.format("[%s] %s", dto.getCategory().getDesc(), dto.getTitle());
    }
}
