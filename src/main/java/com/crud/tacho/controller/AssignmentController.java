package com.crud.tacho.controller;

import com.crud.tacho.domain.AssignmentDto;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.exception.DriverNotFoundException;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.mapper.AssignmentMapper;
import com.crud.tacho.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public AssignmentDto getAssignment(@PathVariable Long assignmentId) {
        return assignmentMapper.mapToAssignmentDto(assignmentService.getAssignmentById(assignmentId));
    }

    @PostMapping(value = "/assignments/{dutyId}")
    public AssignmentDto createAssignment(@PathVariable Long dutyId) {
        return assignmentMapper.mapToAssignmentDto(assignmentService.createAssignment(dutyId));
    }

    @DeleteMapping(value = "/assignments/{assignmentId}")
    public void deleteAssignment(@PathVariable Long assignmentId) {
        assignmentService.deleteAssignment(assignmentId);

    }

    @PutMapping(value = "/assignment/{assignmentId}/driver/{driverId}")
    public void assignDriver(@PathVariable Long assignmentId, @PathVariable Long driverId) {
        assignmentService.assignDriver(assignmentId, driverId);
    }

}
