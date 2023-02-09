package com.knu.noticesender.notice.utils;

import com.knu.noticesender.notice.dto.NoticeDto;
import com.knu.noticesender.notice.dto.discord.DiscordMessage;
import com.knu.noticesender.notice.dto.discord.DiscordMessage.Embed;
import com.knu.noticesender.notice.dto.discord.DiscordMessage.Embed.Field;
import java.util.ArrayList;
import java.util.List;

public class NoticeDiscordMessageConverter {
    public static DiscordMessage convertToDiscordMessage(String botName, NoticeDto dto) {
        DiscordMessage message = new DiscordMessage();
        message.setUsername(botName);
        message.setEmbeds(getEmbeds(dto));
        return message;
    }

    private static List<Embed> getEmbeds(NoticeDto dto) {
        List<Embed> embeds = new ArrayList<>();
        List<Field> fields = new ArrayList<>();

        fields.add(new Field("번호", dto.getNum().toString()));
        fields.add(new Field("카테고리", dto.getCategory().getDesc()));
        Embed embed = Embed.builder()
                .title("["+ dto.getType().getDesc()+"]"+ dto.getTitle())
                .url(dto.getLink())
                .description("Empty")
                .fields(fields)
                .build();
        embeds.add(embed);
        return embeds;
    }
}
