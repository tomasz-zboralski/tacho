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

    private final Assignment assignment = new Assignment();
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
    private AssignmentService assignmentService;

    @Test
    void shouldGetAllAssignments() {

        //Given & When
        List<Assignment> assignments = Collections.singletonList(assignment);

        when(assignmentRepository.findAll()).thenReturn(assignments);

        List<Assignment> actualAssignments = assignmentService.getAssignments();

        //Then
        assertEquals(assignments, actualAssignments);

    }

    @Test
    void shouldGetAssignmentById() {

        //Given & When
        when(assignmentRepository.findById(ID)).thenReturn(Optional.of(assignment));

        Assignment actualAssignment =  assignmentService.getAssignmentById(ID);

        //Then
        assertEquals(assignment, actualAssignment);

    }

    @Test
    void shouldCreateAssignment() {

        //Given & When
        when(dutyService.getDutyById(ID)).thenReturn(DUTY);
        when(assignmentRepository.save(any())).thenReturn(assignment);

        Assignment actualAssignment = assignmentService.createAssignment(ID);

        //Then
        assertEquals(assignment, actualAssignment);
    }

    @Test
    void shouldAddEntry() {

        //Given & When
        when(assignmentRepository.findById(ID)).thenReturn(Optional.of(assignment));
        when(entryService.getEntryById(ID)).thenReturn(ENTRY);
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        doNothing().when(assignmentService).setStartAndEndTime(anyLong());
        doNothing().when(assignmentService).calculateHoliday(anyLong());

        assignmentService.addEntry(ID, ID);

        //Then
        verify(assignmentRepository, times(1)).findById(ID);
        verify(entryService, times(1)).getEntryById(ID);
        verify(assignmentRepository, times(1)).save(assignment);
        verify(assignmentService, times(1)).setStartAndEndTime(ID);
        verify(assignmentService, times(1)).calculateHoliday(ID);

    }

    @Test
    void shouldAssignDriver() {

        //Given & When
        when(assignmentRepository.findById(ID)).thenReturn(Optional.of(assignment));
        when(driverService.getDriverById(ID)).thenReturn(DRIVER);
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        assignmentService.assignDriver(ID, ID);

        //Then
        verify(assignmentRepository, times(1)).findById(ID);
        verify(driverService, times(1)).getDriverById(ID);
        verify(assignmentRepository, times(1)).save(assignment);
        assertEquals(DRIVER, assignment.getDriver());

    }


    @Test
    void shouldSetStartAndEndTime() {

        //Given & When
        LocalDateTime startTime = LocalDateTime.of(2020, 12, 25, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2020, 12, 25, 10, 20);

        Entry entry = new Entry(EntryType.DRIVE, startTime, endTime);
        Entry entry2 = new Entry(EntryType.DRIVE, startTime, endTime);

        List<Entry> entries = List.of(entry, entry2);

        assignment.setEntries(entries);

        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(assignment));
        when(assignmentRepository.save(any())).thenReturn(assignment);

        assignmentService.setStartAndEndTime(ID);

        //Then
        assertEquals(startTime, assignment.getStartTime());
        assertEquals(endTime, assignment.getEndTime());


    }

    @Test
    void shouldCalculateHoliday() {

        //Given & When
        DUTY.setDutyId(ID);
        assignment.setDuty(DUTY);

        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(assignment));
        when(calendarificService.checkIfHoliday(any())).thenReturn(true);
        when(dutyService.addHoliday(anyLong())).thenReturn(DUTY);
        when(assignmentRepository.save(any())).thenReturn(assignment);

        assignmentService.calculateHoliday(ID);

        //Then
        assertTrue(assignment.isHoliday());
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
