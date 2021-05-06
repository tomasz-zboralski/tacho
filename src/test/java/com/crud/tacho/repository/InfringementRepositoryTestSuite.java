package com.crud.tacho.repository;

import com.crud.tacho.domain.Infringement;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InfringementRepositoryTestSuite {

    @Autowired
    InfringementRepository infringementRepository;

    @Test
    void testSaveInfringement() {

        //Given
        Infringement infringement = new Infringement();

        //When
        infringementRepository.save(infringement);
        Long infringementId = infringement.getInfringementId();

        //Then
        assertTrue(infringementRepository.findById(infringementId).isPresent());

        //CleanUp
        infringementRepository.deleteById(infringementId);

    }

    @Test
    void testFindAllInfringements() {

        Infringement infringement1 = new Infringement();
        Infringement infringement2 = new Infringement();

        //When
        infringementRepository.save(infringement1);
        infringementRepository.save(infringement2);

        Long infringement1Id = infringement1.getInfringementId();
        Long infringement2Id = infringement2.getInfringementId();

        int infringements = infringementRepository.findAll().size();

        //Then
        assertEquals(2, infringements);

        //CleanUp
        infringementRepository.deleteById(infringement1Id);
        infringementRepository.deleteById(infringement2Id);

    }

    @Test
    void testDeleteById() {

        //Given
        Infringement infringement = new Infringement();

        //When
        infringementRepository.save(infringement);
        Long infringementId = infringement.getInfringementId();
        infringementRepository.deleteById(infringementId);

        //Then
        assertFalse(infringementRepository.findById(infringementId).isPresent());

    }
}
