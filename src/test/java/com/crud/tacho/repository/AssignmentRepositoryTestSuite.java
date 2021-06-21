package com.crud.tacho.repository;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Duty;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class AssignmentRepositoryTestSuite {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Test
    void testSaveAssignment() {

        //Given
        Assignment assignment = new Assignment(new Duty());

        //When
        assignmentRepository.save(assignment);
        Long id = assignment.getAssignmentId();

        //Then
        assertTrue(assignmentRepository.findById(id).isPresent());

        //CleanUp
        assignmentRepository.deleteById(id);

    }

    @Test
    void testFindAllAssignments() {

        //Given
        Assignment assignment1 = new Assignment(new Duty());
        Assignment assignment2 = new Assignment(new Duty());

        //When
        assignmentRepository.save(assignment1);
        assignmentRepository.save(assignment2);

        Long assignment1Id = assignment1.getAssignmentId();
        Long assignment2Id = assignment2.getAssignmentId();

        int result = assignmentRepository.findAll().size();

        //Then
        assertEquals(2, result);

        //CleanUp
        assignmentRepository.deleteById(assignment1Id);
        assignmentRepository.deleteById(assignment2Id);

    }

    @Test
    void testDeleteById() {

        //Given
        Assignment assignment = new Assignment(new Duty());

        //When
        assignmentRepository.save(assignment);
        Long id = assignment.getAssignmentId();
        assignmentRepository.deleteById(id);

        //Then
        assertFalse(assignmentRepository.findById(id).isPresent());

    }

}