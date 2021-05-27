package com.crud.tacho.service;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Duty;
import com.crud.tacho.domain.Entry;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.DutyNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntryServiceTestSuite {

    @Autowired
    EntryService entryService;
    @Autowired
    AssignmentService assignmentService;
    @Autowired
    DutyService dutyService;

    @Test
    void shouldAddEntry() throws DutyNotFoundException, AssignmentNotFoundException {
        Duty duty = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "AgencyEntryTest", "CompanyEntryTest");
        Duty savedDuty = dutyService.createDuty(duty);

        Assignment assignment = assignmentService.createAssignment(savedDuty.getDutyId());
        LocalDateTime startTime = LocalDateTime.of(2020, 12, 25, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2020, 12, 25, 10, 20);
        Entry entry = new Entry("Drive", startTime, endTime, assignment);
        Entry entry2 = new Entry("Drive", startTime, endTime, assignment);

        entryService.createEntry(entry);
        entryService.createEntry(entry2);
    }

}
