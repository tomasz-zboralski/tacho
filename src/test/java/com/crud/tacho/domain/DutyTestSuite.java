package com.crud.tacho.domain;

import com.crud.tacho.domain.decorator.BonusDecorator;
import com.crud.tacho.domain.decorator.Job;
import com.crud.tacho.domain.decorator.NightOutDecorator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DutyTestSuite {

    @Test
    void testBasicDutyGetAllowance() {

        //Given
        Duty duty = new Duty(BigDecimal.TEN ,BigDecimal.ZERO, "Agency", "Company", new HashSet<>());

        //When
        BigDecimal allowance = duty.getAllowance();

        //Then
        assertEquals(BigDecimal.ZERO, allowance);
    }

//    @Test
//    void testDutyWithNightOut() {
//
//        //Given
//        Job duty = new Duty(BigDecimal.TEN ,BigDecimal.ZERO, "Agency", "Company", new HashSet<>());
//
//        //When
//        duty = new NightOutDecorator(duty);
//        BigDecimal allowance = duty.getAllowance();
//
//        //Then
//        assertEquals(new BigDecimal(25), allowance);
//    }

    @Test
    void testDutyWithNightOutAndDoubleBonus() {

        //Given
        Job duty = new Duty(BigDecimal.TEN ,BigDecimal.ZERO, "Agency", "Company", new HashSet<>());

        //When
        //duty = new NightOutDecorator(duty);
        duty = new BonusDecorator(duty);
        duty = new BonusDecorator(duty);

        BigDecimal allowance = duty.getAllowance();

        //Then
        assertEquals(new BigDecimal(125), allowance);

    }
}
