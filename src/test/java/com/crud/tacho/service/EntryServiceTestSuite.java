package com.crud.tacho.service;

import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.EntryType;
import com.crud.tacho.repository.EntryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EntryServiceTestSuite {

    private static final Long ID = 1L;
    private static final LocalDateTime START_TIME = LocalDateTime.of(2020, 12, 25, 10, 10);
    private static final LocalDateTime END_TIME = LocalDateTime.of(2020, 12, 25, 10, 20);
    private static final Entry ENTRY = new Entry(EntryType.DRIVE, START_TIME, END_TIME);

    @Mock
    EntryRepository entryRepository;

    @InjectMocks
    EntryService entryService;

    @Test
    void shouldCreateEntry() {

        //Given & When
        when(entryRepository.save(ENTRY)).thenReturn(ENTRY);

        Entry actualEntry = entryService.createEntry(ENTRY);

        //Then
        assertEquals(ENTRY, actualEntry);

    }

    @Test
    void shouldRetrieveAllEntries() {

        //Given & When
        List<Entry> entries = Collections.singletonList(ENTRY);
        when(entryRepository.findAll()).thenReturn(entries);

        List<Entry> actualEntries = entryService.getEntries();

        //Then
        assertEquals(entries, actualEntries);

    }

    @Test
    void shouldRetrieveEntryByAssignmentId() {

        //Given & When
        List<Entry> entries = Collections.singletonList(ENTRY);
        when(entryRepository.findAllByAssignment_AssignmentId(ID)).thenReturn(entries);

        List<Entry> actualEntries = entryService.getEntriesByAssignmentId(ID);

        assertEquals(entries, actualEntries);

    }

    @Test
    void shouldRetrieveEntriesByType() {

        //Given & When

        List<Entry> entries = Collections.singletonList(ENTRY);
        when(entryRepository.findAllByType(EntryType.DRIVE)).thenReturn(entries);

        List<Entry> actualEntries = entryService.getEntriesByType(EntryType.DRIVE);

        assertEquals(entries, actualEntries);

    }

    @Test
    void shouldDeleteEntryById() {

        //Given & When
        doNothing().when(entryRepository).deleteById(ID);
        entryService.deleteEntry(ID);

        //Then
        verify(entryRepository,times(1)).deleteById(ID);

    }

}
