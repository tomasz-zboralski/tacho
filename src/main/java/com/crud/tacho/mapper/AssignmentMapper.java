package com.crud.tacho.mapper;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.AssignmentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentMapper {

    public Assignment mapToAssignment(AssignmentDto assignmentDto) {
        return new Assignment(
                assignmentDto.getAssignmentId(),
                assignmentDto.getStartTime(),
                assignmentDto.getEndTime(),
                assignmentDto.getDuration(),
                assignmentDto.getDuty(),
                assignmentDto.getDriver(),
                assignmentDto.getInvoice(),
                assignmentDto.getEntries(),
                assignmentDto.getInfringements()
        );
    }

    public AssignmentDto mapToAssignmentDto(Assignment assignment) {
        return new AssignmentDto(
                assignment.getAssignmentId(),
                assignment.getStartTime(),
                assignment.getEndTime(),
                assignment.getDuration(),
                assignment.getDuty(),
                assignment.getDriver(),
                assignment.getInvoice(),
                assignment.getEntries(),
                assignment.getInfringements()
        );
    }

    public List<AssignmentDto> mapToAssignmentDtoList(List<Assignment> assignments) {
        return assignments.stream()
                .map(this::mapToAssignmentDto)
                .collect(Collectors.toList());
    }

    public List<Assignment> mapToAssignmentList(List<AssignmentDto> assignmentsDto) {
        return assignmentsDto.stream()
                .map(this::mapToAssignment)
                .collect(Collectors.toList());
    }
}
