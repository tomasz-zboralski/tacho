package com.crud.tacho.service;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Duty;
import com.crud.tacho.domain.Entry;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.exception.EntryNotFoundException;
import com.crud.tacho.repository.AssignmentRepository;
import com.crud.tacho.repository.EntryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EntryServiceTestSuite {

    @Autowired
    EntryService entryService;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    DutyService dutyService;

    @Test
    void shouldCreateEntry() {

        //Given
        LocalDateTime startTime = LocalDateTime.of(2020, 12, 25, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2020, 12, 25, 10, 20);
        Entry entry = new Entry("Drive", startTime, endTime);

        //When
        Entry savedEntry = entryService.createEntry(entry);

        //Then
        assertEquals(1, entryService.getEntries().size());


        //CleanUp
        entryService.deleteEntry(savedEntry.getEntryId());

    }

    @Test
    void shouldRetrieveAllEntries() {

        //Given
        LocalDateTime startTime = LocalDateTime.of(2020, 12, 25, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2020, 12, 25, 10, 20);

        Entry entry = new Entry("Drive", startTime, endTime);
        Entry entry2 = new Entry("Drive", startTime, endTime);

        entryRepository.save(entry);
        entryRepository.save(entry2);

        //When

        List<Entry> entries = entryService.getEntries();

        //Then
        assertEquals(2, entries.size());

        //CleanUp
        entryRepository.deleteById(entry.getEntryId());
        entryRepository.deleteById(entry2.getEntryId());

    }

    @Test
    void shouldRetrieveEntryByAssignmentId() {

        //Given
        LocalDateTime startTime = LocalDateTime.of(2020, 12, 25, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2020, 12, 25, 10, 20);

        Entry entry = new Entry("Drive", startTime, endTime);
        Entry entry2 = new Entry("Drive", startTime, endTime);

        Assignment assignment = new Assignment();

        entry.setAssignment(assignment);
        entry2.setAssignment(assignment);

        assignment.getEntries().add(entry);
        assignment.getEntries().add(entry2);

        Assignment savedAssignment = assignmentRepository.save(assignment);

        //When
        List<Entry> entries = entryService.getEntriesByAssignmentId(savedAssignment.getAssignmentId());

        //Then
        assertEquals(2, entries.size());

        //CleanUp
        assignmentService.deleteAssignment(savedAssignment.getAssignmentId());
    }

    @Test
    void shouldRetrieveEntriesByType() {

        //Given
        LocalDateTime startTime = LocalDateTime.of(2020, 12, 25, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2020, 12, 25, 10, 20);

        Entry entry = new Entry("Drive", startTime, endTime);
        Entry entry2 = new Entry("Rest", startTime, endTime);
        Entry entry3 = new Entry("Drive", startTime, endTime);

        //When

        entryRepository.save(entry);
        entryRepository.save(entry2);
        entryRepository.save(entry3);

        List<Entry> entries = entryService.getEntriesByType("Drive");

        //Then
        assertEquals(2, entries.size());

        //CleanUp
        entryRepository.deleteById(entry.getEntryId());
        entryRepository.deleteById(entry2.getEntryId());
        entryRepository.deleteById(entry3.getEntryId());


    }

    @Test
    void shouldDeleteEntryById() {

        //Given
        LocalDateTime startTime = LocalDateTime.of(2020, 12, 25, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2020, 12, 25, 10, 20);

        Entry entry = new Entry("Drive", startTime, endTime);

        entryRepository.save(entry);

        //When
        entryService.deleteEntry(entry.getEntryId());

        //Then
        assertFalse(entryRepository.findById(entry.getEntryId()).isPresent());
    }

}
