package com.crud.tacho.controller;

import com.crud.tacho.domain.Driver;
import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.EntryDto;
import com.crud.tacho.domain.EntryType;
import com.crud.tacho.mapper.EntryMapper;
import com.crud.tacho.service.EntryService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WithMockUser(value = "driver", roles = "DRIVER")
@WebMvcTest(EntryController.class)
class EntryControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntryService entryService;

    @MockBean
    private EntryMapper entryMapper;

    @Test
    void getEntriesByType() throws Exception {
        //Given
        List<Entry> entries = new ArrayList<>();

        when(entryService.getEntriesByType(EntryType.DRIVE)).thenReturn(entries);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/entries/type/DRIVE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

    }

    @Test
    void getEntriesByAssignmentId() throws Exception {
        //Given
        List<Entry> entries = new ArrayList<>();

        when(entryService.getEntriesByAssignmentId(1L)).thenReturn(entries);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/entries/assignment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

    }

    @Test
    void getAllEntries() throws Exception {
        //Given
        List<Entry> entries = new ArrayList<>();

        when(entryService.getEntries()).thenReturn(entries);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/entries")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));

    }

    @Test
    void getEntry() throws Exception {
        //Given
        Entry entry = new Entry(EntryType.DRIVE, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10));
        EntryDto entryDto = new EntryDto(1L, EntryType.DRIVE, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10));


        when(entryService.getEntryById(1L)).thenReturn(entry);
        when(entryMapper.mapToEntryDto(entry)).thenReturn(entryDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/entries/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.entryId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", Matchers.is(EntryType.DRIVE.toString())));

    }

    @Test
    void createEntry() throws Exception {
        //Given
        Entry entry = new Entry();
        EntryDto entryDto = new EntryDto();

        when(entryMapper.mapToEntryDto(entry)).thenReturn(entryDto);
        when(entryMapper.mapToEntry(entryDto)).thenReturn(entry);
        when(entryService.createEntry(any(Entry.class))).thenReturn(entry);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(entryDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/entries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void updateEntry() throws Exception {
        //Given
        Entry entry = new Entry();
        EntryDto entryDto = new EntryDto();

        when(entryMapper.mapToEntryDto(entry)).thenReturn(entryDto);
        when(entryMapper.mapToEntry(entryDto)).thenReturn(entry);
        when(entryService.createEntry(any(Entry.class))).thenReturn(entry);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(entryDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/entries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void deleteEntry() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/entries/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}