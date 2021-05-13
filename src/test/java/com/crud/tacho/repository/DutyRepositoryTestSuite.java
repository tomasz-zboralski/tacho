package com.crud.tacho.repository;

import com.crud.tacho.domain.Duty;
import com.crud.tacho.domain.decorator.BonusDecorator;
import com.crud.tacho.domain.decorator.Job;
import com.crud.tacho.domain.decorator.NightOutDecorator;
import com.crud.tacho.mapper.DutyMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DutyRepositoryTestSuite {

    @Autowired
    DutyRepository dutyRepository;

    @Autowired
    DutyMapper dutyMapper;

    @Test
    void testSaveDuty() {

        //Given
        Duty duty = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company", new HashSet<>());

        //When
        dutyRepository.save(duty);
        Long dutyId = duty.getDutyId();

        //Then
        assertTrue(dutyRepository.findById(dutyId).isPresent());

        //CleanUp
        dutyRepository.deleteById(dutyId);

    }

    @Test
    void testSaveDutyWithDecorators() {

        //Given
        Job duty = new Duty(BigDecimal.TEN ,BigDecimal.ONE, "Agency", "Company", new HashSet<>());

        //When
        duty = new NightOutDecorator(duty);
//        duty = new BonusDecorator(duty);
//        duty = new BonusDecorator(duty);
        Duty testDuty = dutyMapper.mapJobToDuty(duty);

        dutyRepository.save(testDuty);

        Long dutyId = testDuty.getDutyId();
        Duty savedDuty = dutyRepository.findById(dutyId).get();
        savedDuty = dutyMapper.mapJobToDuty(new NightOutDecorator(savedDuty));
        dutyRepository.save(savedDuty);
    }

    @Test
    void testFindAllDuties() {

        //Given
        Duty duty1 = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "Agency1", "Company1", new HashSet<>());
        Duty duty2 = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "Agency2", "Company2", new HashSet<>());


        //When
        dutyRepository.save(duty1);
        dutyRepository.save(duty2);

        Long duty1Id = duty1.getDutyId();
        Long duty2Id = duty2.getDutyId();

        int duties = dutyRepository.findAll().size();

        //Then
        assertEquals(2, duties);

        //CleanUp
        dutyRepository.deleteById(duty1Id);
        dutyRepository.deleteById(duty2Id);
    }

    @Test
    void testDeleteById() {
        //Given
        Duty duty = new Duty(BigDecimal.TEN, BigDecimal.ZERO, "Agency", "Company", new HashSet<>());

        //When
        dutyRepository.save(duty);
        Long dutyId = duty.getDutyId();
        dutyRepository.deleteById(dutyId);

        //Then
        assertFalse(dutyRepository.findById(dutyId).isPresent());
    }
}
