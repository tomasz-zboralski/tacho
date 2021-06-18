package com.crud.tacho.calendarific.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CalendarificConfig {

    @Value("${calendarific.api.endpoint.prod}")
    private String calendarificApiEndpoint;

    @Value("${calendarific.api.key}")
    private String calendarificApiKey;
}
