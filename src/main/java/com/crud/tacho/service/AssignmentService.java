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
    private final DutyRepository dutyRepository;
    private final DriverRepository driverRepository;
    private final DutyService dutyService;
    private final CalendarificService calendarificService;
    private final EntryService entryService;
    //private final AssignmentMapper assignmentMapper;

    public List<Assignment> getAssignments() {
        return assignmentRepository.findAll();
    }

//    public AssignmentDto getAssignmentById(Long id) throws AssignmentNotFoundException {
//        return assignmentMapper.mapToAssignmentDto(
//                assignmentRepository.findById(id)
//                        .orElseThrow(AssignmentNotFoundException::new));
//    }

    public Assignment getAssignmentById(Long id) throws AssignmentNotFoundException {
        return assignmentRepository.findById(id).orElseThrow(AssignmentNotFoundException::new);
    }

    public Assignment createAssignment(Long dutyId) throws DutyNotFoundException {
        Duty duty = dutyRepository.findById(dutyId).orElseThrow(DutyNotFoundException::new);
        //Duty duty = new Duty();
        Assignment assignment = new Assignment(duty);
        return assignmentRepository.save(assignment);
    }

    public void addEntry(Long assignmentId, Long entryId) throws AssignmentNotFoundException, EntryNotFoundException, DutyNotFoundException {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(AssignmentNotFoundException::new);
        Entry entry = entryService.getEntryById(entryId);
        assignment.getEntries().add(entry);
        setStartAndEndTime(assignmentId);
        calculateHoliday(assignmentId);

        assignmentRepository.save(assignment);


    }

    public void assignDriver(Long assignmentId, Long driverId) throws AssignmentNotFoundException, DriverNotFoundException {
//        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
//        Optional<Driver> driver = driverRepository.findById(driverId);
//        assignment.orElseThrow(AssignmentNotFoundException::new)
//                .setDriver(driver.orElseThrow(DriverNotFoundException::new));
//        assignmentRepository.save(assignment.orElseThrow(AssignmentNotFoundException::new));

    }

    public void setStartAndEndTime(Long assignmentId) throws AssignmentNotFoundException {
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

    public void calculateHoliday(Long assignmentId) throws AssignmentNotFoundException, DutyNotFoundException {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(AssignmentNotFoundException::new);
//        Assignment assignment1 = assignment.orElseThrow(AssignmentNotFoundException::new);

        if (!calendarificService.checkIfHoliday(assignment.getStartTime()) & !assignment.isHoliday()) {
                dutyService.addHoliday(assignment.getDuty().getDutyId());
                assignment.setHoliday(true);
                assignmentRepository.save(assignment);
            }

//        if (assignment1.getStartTime() != null) {
//            if (!calendarificService.fetchHolidays(assignment1.getStartTime()).isEmpty() & !assignment1.isHoliday()) {
//                dutyService.addHoliday(assignment1.getDuty().getDutyId());
//                assignment1.setHoliday(true);
//                assignmentRepository.save(assignment.get());
//            }
//        }


    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }




}
