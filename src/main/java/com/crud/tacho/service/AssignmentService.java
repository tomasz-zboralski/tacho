package com.crud.tacho.service;

import com.crud.tacho.domain.*;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.DriverNotFoundException;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.mapper.AssignmentMapper;
import com.crud.tacho.repository.AssignmentRepository;
import com.crud.tacho.repository.DriverRepository;
import com.crud.tacho.repository.DutyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final DutyRepository dutyRepository;
    private final DriverRepository driverRepository;
    private final DutyService dutyService;
    private final CalendarificService calendarificService;
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
        Assignment assignment = new Assignment(duty);
        return assignmentRepository.save(assignment);
    }

    public void assignDriver(Long assignmentId, Long driverId) throws AssignmentNotFoundException, DriverNotFoundException {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Optional<Driver> driver = driverRepository.findById(driverId);
        assignment.orElseThrow(AssignmentNotFoundException::new)
                .setDriver(driver.orElseThrow(DriverNotFoundException::new));
        assignmentRepository.save(assignment.orElseThrow(AssignmentNotFoundException::new));

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

            assignmentRepository.save(assignment.get());
        }
    }

    public void calculateHoliday(Long assignmentId) throws AssignmentNotFoundException, DutyNotFoundException {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Assignment assignment1 = assignment.orElseThrow(AssignmentNotFoundException::new);

        if (assignment1.getStartTime() != null) {
            if (!calendarificService.fetchHolidays(assignment1.getStartTime()).isEmpty() & !assignment1.isHoliday()) {
                dutyService.addHoliday(assignment1.getDuty().getDutyId());
                assignmentRepository.save(assignment.get());
            }
        }


    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }




}
