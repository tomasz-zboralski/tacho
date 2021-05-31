package com.crud.tacho.controller;

import com.crud.tacho.domain.Driver;
import com.crud.tacho.domain.DriverDto;
import com.crud.tacho.exception.DriverNotFoundException;
import com.crud.tacho.mapper.DriverMapper;
import com.crud.tacho.service.DriverService;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(DriverController.class)
class DriverControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;

    @MockBean
    private DriverMapper driverMapper;

    @Test
    void createDriver() throws Exception {

        //Given
        Driver driver = new Driver(1L, "John");
        DriverDto driverDto = new DriverDto(1L, "John");

        when(driverMapper.mapToDriver(driverDto)).thenReturn(driver);
        when(driverMapper.mapToDriverDto(driver)).thenReturn(driverDto);
        when(driverService.createDriver(any(Driver.class))).thenReturn(driver);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(driverDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void getDrivers() throws Exception {
        //Given
        Set<Driver> drivers = new HashSet<>();

        when(driverService.getDrivers()).thenReturn(drivers);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/drivers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

    }

    @Test
    void deleteDriver() throws Exception {

        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/drivers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void updateDriver() throws Exception {
        Driver driver = new Driver(1L, "John");
        DriverDto driverDto = new DriverDto(1L, "John");

        when(driverMapper.mapToDriver(driverDto)).thenReturn(driver);
        when(driverMapper.mapToDriverDto(driver)).thenReturn(driverDto);

        when(driverService.createDriver(any(Driver.class))).thenReturn(driver);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(driverDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}