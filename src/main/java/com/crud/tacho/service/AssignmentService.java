package com.crud.tacho.service;

import com.crud.tacho.domain.*;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.DriverNotFoundException;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.exception.EntryNotFoundException;
import com.crud.tacho.repository.AssignmentRepository;
import com.crud.tacho.repository.DriverRepository;
import com.crud.tacho.repository.DutyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final DutyService dutyService;
    private final CalendarificService calendarificService;
    private final EntryService entryService;
    private final DriverService driverService;

    public List<Assignment> getAssignments() {
        return assignmentRepository.findAll();
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id).orElseThrow(AssignmentNotFoundException::new);
    }

    public Assignment createAssignment(Long dutyId) {
        Duty duty = dutyService.getDutyById(dutyId);
        Assignment assignment = new Assignment(duty);
        return assignmentRepository.save(assignment);
    }

    public void addEntry(Long assignmentId, Long entryId) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(AssignmentNotFoundException::new);
        Entry entry = entryService.getEntryById(entryId);
        assignment.getEntries().add(entry);
        setStartAndEndTime(assignmentId);
        calculateHoliday(assignmentId);

        assignmentRepository.save(assignment);

    }

    public void assignDriver(Long assignmentId, Long driverId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Driver driver = driverService.getDriverById(driverId);
        assignment.orElseThrow(AssignmentNotFoundException::new).setDriver(driver);
        assignmentRepository.save(assignment.orElseThrow(AssignmentNotFoundException::new));

    }

    public void setStartAndEndTime(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        List<Entry> entries = assignment.orElseThrow(AssignmentNotFoundException::new).getEntries();
        if(!entries.isEmpty()) {
            assignment
                    .orElseThrow(AssignmentNotFoundException::new)
                    .setStartTime(entries.get(0).getStartTime());

            assignment
                    .orElseThrow(AssignmentNotFoundException::new)
                    .setEndTime(entries.get(entries.size() -1).getEndTime());

            assignment
                    .orElseThrow(AssignmentNotFoundException::new)
                    .setDuration(Duration.between(
                            entries.get(0).getStartTime(),
                            entries.get(entries.size() -1).getEndTime())
                    );

            assignmentRepository.save(assignment.get());
        }
    }

    public void calculateHoliday(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(AssignmentNotFoundException::new);

        if (calendarificService.checkIfHoliday(assignment.getStartTime()) & !assignment.isHoliday()) {
                dutyService.addHoliday(assignment.getDuty().getDutyId());
                assignment.setHoliday(true);
                assignmentRepository.save(assignment);
            }
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }

}
