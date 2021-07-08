package com.crud.tacho.service.infringement_calc;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.EntryType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class InfringementCalculator {

    public boolean isDrivingTimeInfringement(Assignment assignment) {
        List<Entry> entryList = assignment.getEntries().stream().collect(Collectors.toList());

        double rest = 0;
        double driving = 0;


        for (Entry entry : entryList) {
            switch (entry.getType()) {
                case REST:
                    double minutes = (double) entry.getDuration().toMinutes();
                    if (rest == 0 && minutes >= 15 && minutes < 45) rest = 15;
                    else if (rest == 0 && minutes >= 45) rest = 45;
                    else if (rest == 15 && minutes >= 30) rest += 30;
                    break;
                case DRIVE:
                    driving += (double) entry.getDuration().toMinutes();
                    break;
            }
            if (rest < 45 && driving > 270) return true;
            if (rest >= 45) {
                driving = 0;
                rest = 0;
            }
        }

        return false;
    }
}
