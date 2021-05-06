package com.crud.tacho.controller;

import com.crud.tacho.domain.AssignmentDto;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.service.AssignmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping(value = "/assignments")
    public List<AssignmentDto> getAssignments() {
        return assignmentService.getAssignments();
    }

    @GetMapping(value = "/assignments/{assignmentId}")
    public AssignmentDto getAssignment(@PathVariable Long assignmentId) throws AssignmentNotFoundException {
        return assignmentService.getAssignmentDto(assignmentId);
    }

    @DeleteMapping(value = "/assignments/{assignmentId}")
    public void deleteAssignment(@PathVariable Long assignmentId) {
        assignmentService.deleteAssignment(assignmentId);

    }

    @PostMapping(value = "/assignments", consumes = APPLICATION_JSON_VALUE)
    public AssignmentDto createAssignment(@RequestBody AssignmentDto assignmentDto) {
        return assignmentService.createAssignment(assignmentDto);
    }

    @PutMapping(value = "/assignments")
    public AssignmentDto updateAssignment(@RequestBody AssignmentDto assignmentDto) {
        return assignmentService.createAssignment(assignmentDto);
    }

}
