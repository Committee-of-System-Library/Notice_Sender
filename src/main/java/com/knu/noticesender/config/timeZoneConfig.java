package com.knu.noticesender.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
@Slf4j
public class timeZoneConfig {

    @PostConstruct
    void setTimeZoneSeoul() {
        log.info("time zone(Asia/Seoul) setting complete");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
}
