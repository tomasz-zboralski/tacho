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

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }




}
