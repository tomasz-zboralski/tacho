package com.crud.tacho.calendarific.client;

import com.crud.tacho.calendarific.config.CalendarificConfig;
import com.crud.tacho.domain.CalendarificResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CalendarificClient {

    private final RestTemplate restTemplate;
    private final CalendarificConfig calendarificConfig;

    private URI holidaysURL(LocalDateTime date) {

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        return UriComponentsBuilder.fromHttpUrl(calendarificConfig.getCalendarificApiEndpoint())
                .queryParam("api_key", calendarificConfig.getCalendarificApiKey())
                .queryParam("country", "GB")
                .queryParam("year", year)
                .queryParam("month", month)
                .queryParam("day", day)
                .build().encode().toUri();
    }

    public List<CalendarificResponse.Response.HolidayDto> getHolidays(LocalDateTime date) {

        try {

            CalendarificResponse calendarificResponse = restTemplate.getForObject(
                    holidaysURL(date), CalendarificResponse.class
            );

            return calendarificResponse
                    .getResponse()
                    .getHolidayDtoList()
                    .stream()
                    .collect(Collectors.toList());

        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
}
