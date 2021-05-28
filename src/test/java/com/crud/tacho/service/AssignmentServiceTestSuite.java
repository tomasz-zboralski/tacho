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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentServiceTestSuite {

    @Autowired
    EntryService entryService;
    @Autowired
    AssignmentService assignmentService;
    @Autowired
    DutyService dutyService;
    @Autowired
    AssignmentRepository assignmentRepository;

    @Test
    void shouldAddEntriesAndCalculateTimeAndCheckIfHoliday() throws DutyNotFoundException, AssignmentNotFoundException, EntryNotFoundException {

        //Given
        LocalDateTime startTime = LocalDateTime.of(2020, 12, 25, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2020, 12, 25, 10, 20);

        Entry entry = new Entry("Drive", startTime, endTime);
        Entry entry2 = new Entry("Drive", startTime, endTime);

        Duty duty = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "AgencyEntryTest", "CompanyEntryTest");

        //When
        Duty savedDuty = dutyService.createDuty(duty);

        Assignment assignment = assignmentService.createAssignment(savedDuty.getDutyId());

        entry.setAssignment(assignment);
        entry2.setAssignment(assignment);
        entryService.createEntry(entry);
        entryService.createEntry(entry2);

        assignmentService.addEntry(assignment.getAssignmentId(), entry.getEntryId());
        assignmentService.addEntry(assignment.getAssignmentId(), entry2.getEntryId());

        //Then
        assertEquals(2, assignment.getEntries().size());
        assertEquals(entry.getAssignment().getAssignmentId(), assignment.getAssignmentId());
        assertTrue(assignment.getEntries().contains(entry2));
        assertEquals(startTime ,assignment.getStartTime());
        assertEquals(endTime ,assignment.getEndTime());
        assertEquals(new BigDecimal(25), duty.getAllowance());
        assertTrue(assignment.isHoliday());

    }
}
