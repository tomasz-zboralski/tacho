package com.crud.tacho.calendarific.client;

import com.crud.tacho.domain.CalendarificResponse;
import lombok.AllArgsConstructor;
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

    private URI holidaysURL(LocalDateTime date) {

//        int year = LocalDateTime.now().getYear();
//        int month = LocalDateTime.now().getMonthValue();
//        int day = LocalDateTime.now().getDayOfMonth();

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        return UriComponentsBuilder.fromHttpUrl("https://calendarific.com/api/v2/holidays")
                .queryParam("api_key", "c5daf78fe6e1b70cd62a915c5e26e40724edb4ca")
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
