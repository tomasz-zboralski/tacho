package com.crud.tacho.controller;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.AssignmentDto;
import com.crud.tacho.domain.Duty;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.DriverNotFoundException;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.mapper.AssignmentMapper;
import com.crud.tacho.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final AssignmentMapper assignmentMapper;

    @GetMapping(value = "/assignments")
    public List<AssignmentDto> getAssignments() {
        return assignmentMapper.mapToAssignmentDtoList(assignmentService.getAssignments());
    }

    @GetMapping(value = "/assignments/{assignmentId}")
    public AssignmentDto getAssignment(@PathVariable Long assignmentId) throws AssignmentNotFoundException {
        return assignmentMapper.mapToAssignmentDto(assignmentService.getAssignmentById(assignmentId));
    }

    @DeleteMapping(value = "/assignments/{assignmentId}")
    public void deleteAssignment(@PathVariable Long assignmentId) {
        assignmentService.deleteAssignment(assignmentId);

    }

    @PostMapping(value = "/assignments")
    public AssignmentDto createAssignment(@PathVariable Long dutyId) throws DutyNotFoundException {
        //Duty duty = new Duty();
        return assignmentMapper.mapToAssignmentDto(assignmentService.createAssignment(dutyId));
    }

    @PostMapping(value = "/assignment/{assignmentId}/driver/{driverId}")
    public void assignDriver(@PathVariable Long assignmentId, @PathVariable Long driverId) throws DriverNotFoundException, AssignmentNotFoundException {
        assignmentService.assignDriver(assignmentId, driverId);
    }

//    @PostMapping(value = "/assignments", consumes = APPLICATION_JSON_VALUE)
//    public AssignmentDto createAssignment(@RequestBody AssignmentDto assignmentDto) throws AssignmentNotFoundException {
//        Assignment assignment = assignmentMapper.mapToAssignment(assignmentDto);
//        return assignmentMapper.mapToAssignmentDto(assignmentService.createAssignment(assignment));
//    }

//    @PutMapping(value = "/assignments")
//    public AssignmentDto updateAssignment(@RequestBody AssignmentDto assignmentDto) throws AssignmentNotFoundException {
//        Assignment assignment = assignmentMapper.mapToAssignment(assignmentDto);
//        return assignmentMapper.mapToAssignmentDto(assignmentService.createAssignment(assignment));
//    }

}
