package com.crud.tacho.controller;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.AssignmentDto;
import com.crud.tacho.mapper.AssignmentMapper;
import com.crud.tacho.service.AssignmentService;
import com.google.gson.Gson;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WithMockUser(value = "driver", roles = "DRIVER")
@WebMvcTest(AssignmentController.class)
class AssignmentControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentService assignmentService;

    @MockBean
    private AssignmentMapper assignmentMapper;

    @Test
    void getAssignments() throws Exception {

        //Given
        List<Assignment> assignments = new ArrayList<>();

        //When
        when(assignmentService.getAssignments()).thenReturn(assignments);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/assignments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void getAssignment() throws Exception {

        //Given
        Assignment assignment = new Assignment();
        AssignmentDto assignmentDto = new AssignmentDto();

        //When
        when(assignmentService.getAssignmentById(1L)).thenReturn(assignment);
        when(assignmentMapper.mapToAssignmentDto(any(Assignment.class))).thenReturn(assignmentDto);

        //Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/assignments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void deleteAssignment() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/assignments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void createAssignment() throws Exception {
        //Given
        Assignment assignment = new Assignment();
        AssignmentDto assignmentDto = new AssignmentDto();

        //When
        when(assignmentMapper.mapToAssignment(assignmentDto)).thenReturn(assignment);
        when(assignmentMapper.mapToAssignmentDto(assignment)).thenReturn(assignmentDto);
        when(assignmentService.createAssignment(1L)).thenReturn(assignment);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(assignmentDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/assignments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void assignDriver() throws Exception {
        //Given
        Assignment assignment = new Assignment();
        AssignmentDto assignmentDto = new AssignmentDto();

        //When
        when(assignmentMapper.mapToAssignment(assignmentDto)).thenReturn(assignment);
        when(assignmentMapper.mapToAssignmentDto(assignment)).thenReturn(assignmentDto);

        doNothing().when(assignmentService).assignDriver(1L, 1L);


        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/assignments/1/driver/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}