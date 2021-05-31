package com.crud.tacho.calendarific.client;

import com.crud.tacho.domain.CalendarificResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CalendarificClientTestSuite {

    @Autowired
    CalendarificClient calendarificClient;

    @Test
    void shouldFindChristmas() {

        //Given
        LocalDateTime holiday = LocalDateTime.of(2020,12,25, 12,12 );

        //When
        List<CalendarificResponse.Response.HolidayDto> holidayDtoList = calendarificClient.getHolidays(holiday);

        //Then
        assertEquals(1, holidayDtoList.size());
        assertEquals("Christmas Day", holidayDtoList.get(0).getName());
    }

}