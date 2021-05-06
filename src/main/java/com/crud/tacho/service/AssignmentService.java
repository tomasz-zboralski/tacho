package com.crud.tacho.service;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.AssignmentDto;
import com.crud.tacho.exception.AssignmentNotFoundException;
import com.crud.tacho.mapper.AssignmentMapper;
import com.crud.tacho.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    public List<AssignmentDto> getAssignments() {
        return assignmentMapper.mapToAssignmentDtoList(
                assignmentRepository.findAll());
    }

    public AssignmentDto getAssignmentDto(Long id) throws AssignmentNotFoundException {
        return assignmentMapper.mapToAssignmentDto(
                assignmentRepository.findById(id)
                        .orElseThrow(AssignmentNotFoundException::new));
    }

    public AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Assignment assignment = assignmentMapper.mapToAssignment(assignmentDto);
        assignmentRepository.save(assignment);
        return assignmentMapper.mapToAssignmentDto(assignment);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }




}
