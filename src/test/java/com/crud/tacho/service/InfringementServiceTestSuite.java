package com.crud.tacho.service;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Infringement;
import com.crud.tacho.repository.AssignmentRepository;
import com.crud.tacho.repository.InfringementRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class InfringementServiceTestSuite {

    @Autowired
    InfringementService infringementService;

    @Autowired
    InfringementRepository infringementRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Test
    void calculateInfringement() {

        //Given
        Assignment assignment = new Assignment();

        assignment.setStartTime(LocalDateTime.now().minusHours(14));
        assignment.setEndTime(LocalDateTime.now());
        Duration duration = Duration.ofHours(14);
        assignment.setDuration(duration);

        //When
        assignmentRepository.save(assignment);
        infringementService.calculateInfringement(assignment);

        List<Infringement> infringements = infringementService.getAllInfringements();

        //Then
        assertEquals(1, infringements.size());

        //CleanUp
        for (Infringement infringement:infringements) {
            infringementRepository.deleteById(infringement.getInfringementId());
        }
        assignmentRepository.deleteById(assignment.getAssignmentId());
    }

    @Test
    void getAllValidInfringements() {
        //Given
        Assignment assignment = new Assignment();

        assignment.setStartTime(LocalDateTime.now().minusHours(14));
        assignment.setEndTime(LocalDateTime.now());
        Duration duration = Duration.ofHours(14);
        assignment.setDuration(duration);

        //When
        assignmentRepository.save(assignment);
        infringementService.calculateInfringement(assignment);

        List<Infringement> infringements = infringementService.getAllValidInfringements();

        //Then
        assertEquals(1, infringements.size());

        //CleanUp
        for (Infringement infringement:infringements) {
            infringementRepository.deleteById(infringement.getInfringementId());
        }
        assignmentRepository.deleteById(assignment.getAssignmentId());

    }

    @Test
    void deleteAllNotValidInfringements() {
        //Given
        Assignment assignment = new Assignment();

        assignment.setStartTime(LocalDateTime.now().minusDays(30).minusHours(14));
        assignment.setEndTime(LocalDateTime.now().minusDays(30));
        Duration duration = Duration.ofHours(14);
        assignment.setDuration(duration);

        //When
        Assignment savedAssignment = assignmentRepository.save(assignment);
        infringementService.calculateInfringement(assignment);

        infringementService.deleteAllNotValidInfringements();

        List<Infringement> infringements = infringementService.getAllInfringements();

        //Then
        assertEquals(0, infringements.size());

        //CleanUp
        assignmentRepository.deleteById(savedAssignment.getAssignmentId());



    }
}