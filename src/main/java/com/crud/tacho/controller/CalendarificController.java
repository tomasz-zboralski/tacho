package com.crud.tacho.controller;

import com.crud.tacho.domain.CalendarificResponse;
import com.crud.tacho.service.CalendarificService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/calendarific")
@AllArgsConstructor
public class CalendarificController {

    private final CalendarificService calendarificService;

    @GetMapping
    public boolean getHolidays(LocalDateTime date) {
        return calendarificService.checkIfHoliday(date);
    }
}
