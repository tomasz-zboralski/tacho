package com.crud.tacho.service;

import com.crud.tacho.calendarific.client.CalendarificClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalendarificServiceTestSuite {

    @Mock
    CalendarificClient calendarificClient;

    @InjectMocks
    CalendarificService calendarificService;

    @Test
    void shouldCheckIfHolidays() {

        // Given & When
        LocalDateTime notHoliday = LocalDateTime.of(2020,12,22, 12,12 );

        when(calendarificClient.getHolidays(notHoliday)).thenReturn(new ArrayList<>());

        boolean isHoliday = calendarificService.checkIfHoliday(notHoliday);

        //Then
        assertFalse(isHoliday);

    }

}