package com.crud.tacho.controller;

import com.crud.tacho.domain.AssignmentDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class AssignmentController {

    @GetMapping(value = "/assignments")
    public List<AssignmentDto> getAssignments() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/assignments/{assignmentId}")
    public AssignmentDto getAssignment(@PathVariable Long assignmentId) {
        return new AssignmentDto();
    }

    @DeleteMapping(value = "/assignments/{assignmentId}")
    public void deleteAssignment(@PathVariable Long assignmentId) {

    }

    @PostMapping(value = "/assignments", consumes = APPLICATION_JSON_VALUE)
    public AssignmentDto createAssignment(@RequestBody AssignmentDto assignmentDto) {
        return new AssignmentDto();
    }

    @PutMapping(value = "/assignments")
    public AssignmentDto updateAssignment(@RequestBody AssignmentDto assignment) {
        return new AssignmentDto();
    }

}
