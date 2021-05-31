package com.crud.tacho.controller;

import com.crud.tacho.domain.Duty;
import com.crud.tacho.domain.DutyDto;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.mapper.DutyMapper;
import com.crud.tacho.service.DutyService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(DutyController.class)
class DutyControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DutyService dutyService;

    @MockBean
    private DutyMapper dutyMapper;

    @Test
    void getDuty() throws Exception {

        //Given
        Duty duty = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");
        DutyDto dutyDto = new DutyDto(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");

        //When
        when(dutyService.getDutyById(1L)).thenReturn(duty);
        when(dutyMapper.mapToDutyDto(duty)).thenReturn(dutyDto);

        //Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/duties/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.agency", Matchers.is("Agency")));

    }

    @Test
    void createDuty() throws Exception {

        //Given
        Duty duty = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");
        DutyDto dutyDto = new DutyDto(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");

        when(dutyMapper.mapToDutyDto(duty)).thenReturn(dutyDto);
        when(dutyMapper.mapJobToDuty(dutyDto)).thenReturn(duty);
        when(dutyService.createDuty(any(Duty.class))).thenReturn(duty);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(dutyDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/duties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void updateDuty() throws Exception {
        //Given
        Duty duty = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");
        DutyDto dutyDto = new DutyDto(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");

        when(dutyMapper.mapToDutyDto(duty)).thenReturn(dutyDto);
        when(dutyMapper.mapJobToDuty(dutyDto)).thenReturn(duty);
        when(dutyService.createDuty(any(Duty.class))).thenReturn(duty);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(dutyDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/duties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void addNightOut() throws Exception {
        //Given
        Duty duty = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");
        DutyDto dutyDto = new DutyDto(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");

        when(dutyMapper.mapToDutyDto(duty)).thenReturn(dutyDto);
        when(dutyMapper.mapJobToDuty(dutyDto)).thenReturn(duty);
        when(dutyService.addNightOut(1L)).thenReturn(duty);
        when(dutyService.createDuty(duty)).thenReturn(duty);


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/duties/nightout/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void addBonus() throws Exception {
        //Given
        Duty duty = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");
        DutyDto dutyDto = new DutyDto(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company");

        when(dutyMapper.mapToDutyDto(duty)).thenReturn(dutyDto);
        when(dutyMapper.mapJobToDuty(dutyDto)).thenReturn(duty);
        when(dutyService.addBonus(1L, BigDecimal.TEN)).thenReturn(duty);
        when(dutyService.createDuty(duty)).thenReturn(duty);


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/duties/1/bonus/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}