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

    public boolean checkIfHoliday(LocalDateTime date) {
        List<CalendarificResponse.Response.HolidayDto> holidayDtoList = calendarificClient.getHolidays(date);
        return holidayDtoList.isEmpty();
    }
}
