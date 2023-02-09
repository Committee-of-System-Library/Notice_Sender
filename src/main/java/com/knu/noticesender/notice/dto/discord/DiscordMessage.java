package com.knu.noticesender.notice.dto.discord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
public class DiscordMessage {
    private Map<String, Object> message = new HashMap<>();
    public void setUsername(String username) {message.put("username", username);}
    public void setEmbeds(List<Embed> embeds) {message.put("embeds", embeds);}
    public void setContent(String content) {message.put("content", content);}

    @Data
    @Builder
    public static class Embed {
        String title;
        String url;
        String description;
        List<Field> fields;

        @Data
        public static class Field {
            String name;
            String value;
            boolean inline;

            public Field(String name, String value, boolean inline) {
                this.name = name;
                this.value = value;
                this.inline = inline;
            }

            public Field(String name, String value) {
                this(name, value, false);
            }
        }
    }
}
