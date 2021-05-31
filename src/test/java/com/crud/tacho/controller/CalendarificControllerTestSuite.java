package com.crud.tacho.controller;

import com.crud.tacho.service.CalendarificService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CalendarificController.class)
class CalendarificControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalendarificService calendarificService;

    @Test
    void testGetHolidays() throws Exception {


        when(calendarificService.checkIfHoliday(LocalDateTime.now())).thenReturn(false);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/calendarific")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

}