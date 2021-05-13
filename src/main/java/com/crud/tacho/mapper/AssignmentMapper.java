package com.crud.tacho.mapper;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.AssignmentDto;
import com.crud.tacho.exception.AssignmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AssignmentMapper {

    private final EntryMapper entryMapper;

    public Assignment mapToAssignment(AssignmentDto assignmentDto) {
        return new Assignment(
                assignmentDto.getAssignmentId(),
                assignmentDto.getStartTime(),
                assignmentDto.getEndTime(),
                assignmentDto.getDuration(),
                assignmentDto.getDuty(),
                assignmentDto.getDriver(),
                assignmentDto.getInvoice(),
                entryMapper.mapToEntryList(assignmentDto.getEntries()),
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
                entryMapper.mapToEntryDtoList(assignment.getEntries()),
                assignment.getInfringements()
        );
    }

    public List<AssignmentDto> mapToAssignmentDtoList(List<Assignment> assignments) {
        return assignments.stream()
                .map(this::mapToAssignmentDto)
                .collect(Collectors.toList());
    }

    public Set<Assignment> mapToAssignmentSet(Set<AssignmentDto> assignmentsDto) {
        return assignmentsDto.stream()
                .map(this::mapToAssignment)
                .collect(Collectors.toSet());
    }

    public Set<AssignmentDto> mapToAssignmentDtoSet(Set<Assignment> assignments) {
        return assignments.stream()
                .map(this::mapToAssignmentDto)
                .collect(Collectors.toSet());
    }
}
