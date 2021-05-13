package com.crud.tacho.controller;

import com.crud.tacho.domain.Duty;
import com.crud.tacho.domain.DutyDto;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.mapper.DutyMapper;
import com.crud.tacho.service.DutyService;
import lombok.RequiredArgsConstructor;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class DutyController {

    private final DutyService dutyService;
    private final DutyMapper dutyMapper;

    @GetMapping(value = "/duties/{dutyId}")
    public DutyDto getDuty(@PathVariable Long dutyId) throws DutyNotFoundException {
        return dutyMapper.mapToDutyDto(dutyService.getDutyById(dutyId));
    }

    @PostMapping(value = "/duties", consumes = APPLICATION_JSON_VALUE)
    public DutyDto createDuty(@RequestBody DutyDto dutyDto) {
        Duty duty = dutyMapper.mapJobToDuty(dutyDto);
        return dutyMapper.mapToDutyDto(dutyService.createDuty(duty));
    }

    @PutMapping(value = "/duties")
    public DutyDto updateDuty(@RequestBody DutyDto dutyDto) {
        Duty duty = dutyMapper.mapJobToDuty(dutyDto);
        return dutyMapper.mapToDutyDto(dutyService.createDuty(duty));
    }

    @PutMapping(value = "/duties/nightout/{dutyId}")
    public DutyDto addNightOut(@PathVariable Long dutyId) throws DutyNotFoundException {
        Duty duty = dutyService.addNightOut(dutyId);
        return dutyMapper.mapToDutyDto(dutyService.createDuty(duty));
    }

    @PutMapping(value = "/duties/{dutyId}/bonus/{value}")
    public DutyDto addBonus(@PathVariable Long dutyId, @PathVariable BigDecimal value) throws DutyNotFoundException {
        Duty duty = dutyService.addBonus(dutyId, value);
        return dutyMapper.mapToDutyDto(dutyService.createDuty(duty));
    }
}
