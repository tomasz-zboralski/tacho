package com.crud.tacho.controller;

import com.crud.tacho.domain.Infringement;
import com.crud.tacho.mapper.InfringementMapper;
import com.crud.tacho.service.InfringementService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WithMockUser(roles = {"DRIVER", "AGENCY"})
@WebMvcTest(InfringementController.class)
class InfringementControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InfringementService infringementService;

    @MockBean
    private InfringementMapper infringementMapper;

    @Test
    void testGetInfringements() throws Exception {

        //Given
        List<Infringement> infringements = new ArrayList<>();

        when(infringementService.getAllInfringements()).thenReturn(infringements);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/infringements")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

}