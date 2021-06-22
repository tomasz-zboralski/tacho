package com.crud.tacho.service;

import com.crud.tacho.domain.*;
import com.crud.tacho.repository.AssignmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTestSuite {

    private static final Assignment ASSIGNMENT = new Assignment();
    private static final Driver DRIVER = new Driver();
    private static final Entry ENTRY = new Entry();
    private static final Duty DUTY = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "AgencyEntryTest", "CompanyEntryTest");
    private static final Long ID = 1L;

    @Mock
    private AssignmentRepository assignmentRepository;

    @Mock
    private DutyService dutyService;
    @Mock
    private CalendarificService calendarificService;
    @Mock
    private EntryService entryService;
    @Mock
    private DriverService driverService;

    @InjectMocks
    @Spy
    AssignmentService assignmentService;

    @Test
    void shouldGetAllAssignments() {

        //Given & When
        List<Assignment> assignments = Collections.singletonList(ASSIGNMENT);

        when(assignmentRepository.findAll()).thenReturn(assignments);

        List<Assignment> actualAssignments = assignmentService.getAssignments();

        //Then
        assertEquals(assignments, actualAssignments);

    }

    @Test
    void shouldGetAssignmentById() {

        //Given & When
        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(ASSIGNMENT));

        Assignment actualAssignment =  assignmentService.getAssignmentById(ID);

        //Then
        assertEquals(ASSIGNMENT, actualAssignment);

    }

    @Test
    void shouldCreateAssignment() {

        //Given & When
        when(dutyService.getDutyById(anyLong())).thenReturn(DUTY);
        when(assignmentRepository.save(any())).thenReturn(ASSIGNMENT);

        Assignment actualAssignment = assignmentService.createAssignment(ID);

        //Then
        assertEquals(ASSIGNMENT, actualAssignment);
    }

    @Test
    void shouldAddEntry() {

        //Given & When
        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(ASSIGNMENT));
        when(entryService.getEntryById(anyLong())).thenReturn(ENTRY);
        when(assignmentRepository.save(any())).thenReturn(ASSIGNMENT);

        doNothing().when(assignmentService).setStartAndEndTime(anyLong());
        doNothing().when(assignmentService).calculateHoliday(anyLong());

        assignmentService.addEntry(ID, ID);

        //Then
        verify(assignmentRepository, times(1)).findById(anyLong());
        verify(entryService, times(1)).getEntryById(anyLong());
        verify(assignmentRepository, times(1)).save(any());
        verify(assignmentService, times(1)).setStartAndEndTime(anyLong());
        verify(assignmentService, times(1)).calculateHoliday(anyLong());

    }

    @Test
    void shouldAssignDriver() {

        //Given & When
        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(ASSIGNMENT));
        when(driverService.getDriverById(anyLong())).thenReturn(DRIVER);
        when(assignmentRepository.save(any())).thenReturn(ASSIGNMENT);

        assignmentService.assignDriver(ID, ID);

        //Then
        verify(assignmentRepository, times(1)).findById(anyLong());
        verify(driverService, times(1)).getDriverById(anyLong());
        verify(assignmentRepository, times(1)).save(any());

    }


    @Test
    void shouldSetStartAndEndTime() {

        //Given & When
        LocalDateTime startTime = LocalDateTime.of(2020, 12, 25, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2020, 12, 25, 10, 20);

        Entry entry = new Entry(EntryType.DRIVE, startTime, endTime);
        Entry entry2 = new Entry(EntryType.DRIVE, startTime, endTime);

        List<Entry> entries = List.of(entry, entry2);

        ASSIGNMENT.setEntries(entries);

        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(ASSIGNMENT));
        when(assignmentRepository.save(any())).thenReturn(ASSIGNMENT);

        assignmentService.setStartAndEndTime(ID);

        //Then
        assertEquals(startTime, ASSIGNMENT.getStartTime());
        assertEquals(endTime, ASSIGNMENT.getEndTime());


    }

    @Test
    void shouldCalculateHoliday() {

        //Given & When
        DUTY.setDutyId(ID);
        ASSIGNMENT.setDuty(DUTY);

        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(ASSIGNMENT));
        when(calendarificService.checkIfHoliday(any())).thenReturn(true);
        when(dutyService.addHoliday(anyLong())).thenReturn(DUTY);
        when(assignmentRepository.save(any())).thenReturn(ASSIGNMENT);

        assignmentService.calculateHoliday(ID);

        //Then
        assertTrue(ASSIGNMENT.isHoliday());
    }

    @Test
    void shouldDeleteAssignmentById() {
        //Given & When
        doNothing().when(assignmentRepository).deleteById(ID);
        assignmentService.deleteAssignment(ID);

        //Then
        verify(assignmentRepository,times(1)).deleteById(ID);
    }

}
