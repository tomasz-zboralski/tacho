package com.crud.tacho.service.infringement_calc;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.EntryType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class InfringementCalculatorTest {

    @Autowired
    InfringementCalculator infringementCalculator;

    @Test
    void shouldBeNoInfringement() {
        //Given

        List<Entry> entries = new ArrayList<>();

        Entry drive1 = new Entry();
        drive1.setDuration(Duration.ofMinutes(60));
        drive1.setType(EntryType.DRIVE);
        entries.add(drive1);

        Entry drive2 = new Entry();
        drive2.setDuration(Duration.ofMinutes(60));
        drive2.setType(EntryType.DRIVE);
        entries.add(drive2);

        Entry rest1 = new Entry();
        rest1.setDuration(Duration.ofMinutes(15));
        rest1.setType(EntryType.REST);
        entries.add(rest1);

        Entry drive3 = new Entry();
        drive3.setDuration(Duration.ofMinutes(60));
        drive3.setType(EntryType.DRIVE);
        entries.add(drive3);

        Entry rest2 = new Entry();
        rest2.setDuration(Duration.ofMinutes(30));
        rest2.setType(EntryType.REST);
        entries.add(rest2);

        Assignment assignment = new Assignment();
        assignment.setEntries(entries);

        //When
        int result = infringementCalculator.isDrivingWithoutBreakInfringement(assignment);

        //Then
        assertEquals(-1, result);

    }

    @Test
    void shouldBeDrivingWithoutBreakInfringement() {
        //Given

        List<Entry> entries = new ArrayList<>();

        Entry drive1 = new Entry();
        drive1.setDuration(Duration.ofMinutes(240));
        drive1.setType(EntryType.DRIVE);
        entries.add(drive1);

        Entry drive2 = new Entry();
        drive2.setDuration(Duration.ofMinutes(30));
        drive2.setType(EntryType.DRIVE);
        entries.add(drive2);

        Entry rest1 = new Entry();
        rest1.setDuration(Duration.ofMinutes(44));
        rest1.setType(EntryType.REST);
        entries.add(rest1);

        Entry drive3 = new Entry();
        drive3.setDuration(Duration.ofMinutes(1));
        drive3.setType(EntryType.DRIVE);
        entries.add(drive3);

        Entry rest2 = new Entry();
        rest2.setDuration(Duration.ofMinutes(30));
        rest2.setType(EntryType.REST);
        entries.add(rest2);

        Assignment assignment = new Assignment();
        assignment.setEntries(entries);

        //When
        int result = infringementCalculator.isDrivingWithoutBreakInfringement(assignment);

        //Then
        assertEquals(271, result);
    }
}