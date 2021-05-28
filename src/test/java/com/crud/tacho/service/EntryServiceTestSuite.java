package com.crud.tacho.service;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Duty;
import com.crud.tacho.domain.Entry;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.exception.EntryNotFoundException;
import com.crud.tacho.repository.AssignmentRepository;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;

//@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntryServiceTestSuite {

    @Autowired
    EntryService entryService;

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

}
