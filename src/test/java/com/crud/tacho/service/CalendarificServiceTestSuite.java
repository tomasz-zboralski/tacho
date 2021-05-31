package com.crud.tacho.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CalendarificServiceTestSuite {

    @Autowired
    CalendarificService calendarificService;

    @Test
    void shouldCheckIfHolidays() {

        //Given
        LocalDateTime holiday = LocalDateTime.of(2020,12,25, 12,12 );

        //When
        boolean isHoliday = calendarificService.checkIfHoliday(holiday);

        //Then
        assertTrue(isHoliday);

    }

}