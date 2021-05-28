package com.crud.tacho.controller;

import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.EntryDto;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.exception.EntryNotFoundException;
import com.crud.tacho.mapper.EntryMapper;
import com.crud.tacho.service.DriverService;
import com.crud.tacho.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Transactional
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class EntryController {

    private final EntryService entryService;
    private final EntryMapper entryMapper;

    @GetMapping(value = "/entries/type/{type}")
    public List<EntryDto> getEntriesByType(@PathVariable String type) {
        return entryMapper.mapToEntryDtoList(
                entryService.getEntriesByType(type)
        );
    }

    @GetMapping(value = "/entries/assignment/{assignmentId}")
    public List<EntryDto> getEntriesByAssignmentId(@PathVariable Long assignmentId) {
        return entryMapper.mapToEntryDtoList(
                entryService.getEntriesByAssignmentId(assignmentId)
        );
    }

    @GetMapping(value = "/entries")
    public List<EntryDto> getAllEntries() {
        return entryMapper.mapToEntryDtoList(
                entryService.getEntries()
        );
    }

    @GetMapping(value = "/entries/{entryId}")
    public EntryDto getEntry(@PathVariable Long entryId) throws EntryNotFoundException {
        return entryMapper.mapToEntryDto(
                entryService.getEntryById(entryId)
        );
    }

    @PostMapping(value = "/entries", consumes = APPLICATION_JSON_VALUE)
    public EntryDto createEntry(@RequestBody EntryDto entryDto) throws AssignmentNotFoundException, DutyNotFoundException {
        Entry entry = entryMapper.mapToEntry(entryDto);

        return entryMapper.mapToEntryDto(entryService.createEntry(entry));
    }

    @PutMapping(value = "/entries")
    public EntryDto updateEntry(@RequestBody EntryDto entryDto) throws AssignmentNotFoundException, DutyNotFoundException {
        Entry entry = entryMapper.mapToEntry(entryDto);

        return entryMapper.mapToEntryDto(entryService.createEntry(entry));
    }

    @DeleteMapping(value = "/entries/{entryId}")
    public void deleteEntry(@PathVariable Long entryId) {
        entryService.deleteEntry(entryId);
    }

}
