package com.crud.tacho.repository;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.EntryType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntryRepositoryTestSuite {

    @Autowired
    EntryRepository entryRepository;

    @Test
    void testSaveEntry() {

        //Given
        //Entry entry = new Entry();
        Entry entry = new Entry(
                EntryType.DRIVE.getType(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                Duration.between(LocalDateTime.now(), LocalDateTime.now()),
                new Assignment(LocalDateTime.now(), LocalDateTime.now().plusDays(1))


        );

        //When
        entryRepository.save(entry);
        Long entryId = entry.getEntryId();

        //Then
        assertTrue(entryRepository.findById(entryId).isPresent());

        //CleanUp
        //entryRepository.deleteById(entryId);

    }

    @Test
    void testFindAllEntries() {

        //Given
        Entry entry1 = new Entry();
        Entry entry2 = new Entry();

        //When
        entryRepository.save(entry1);
        entryRepository.save(entry2);

        Long entry1Id = entry1.getEntryId();
        Long entry2Id = entry2.getEntryId();

        int entries = entryRepository.findAll().size();

        //Then
        assertEquals(2, entries);

        //CleanUp
        entryRepository.deleteById(entry1Id);
        entryRepository.deleteById(entry2Id);

    }

    @Test
    void testFindEntriesByType() {
        List<Entry> entries = entryRepository.findAllByType("Drive");
        System.out.println(entries.size());
    }

    @Test
    void testDeleteById() {
        //Given
        Entry entry = new Entry();

        //When
        entryRepository.save(entry);
        Long entryId = entry.getEntryId();
        entryRepository.deleteById(entryId);

        //Then
        assertFalse(entryRepository.findById(entryId).isPresent());

    }
}
