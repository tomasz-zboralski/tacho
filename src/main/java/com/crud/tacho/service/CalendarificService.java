package com.crud.tacho.service;

import com.crud.tacho.calendarific.client.CalendarificClient;
import com.crud.tacho.domain.CalendarificResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CalendarificService {

    private final CalendarificClient calendarificClient;

    public List<CalendarificResponse.Response.HolidayDto> fetchHolidays(LocalDateTime date) {
        return calendarificClient.getHolidays(date);
    }
}
