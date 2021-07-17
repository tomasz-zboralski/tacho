package com.crud.tacho.service.infringement_calc;

import com.crud.tacho.domain.Assignment;
import com.crud.tacho.domain.Entry;
import com.crud.tacho.domain.Infringement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class InfringementCalculator {

    public int isDrivingWithoutBreakInfringement(Assignment assignment) {
        List<Entry> entryList = new ArrayList<>(assignment.getEntries());

        int rest = 0;
        int driving = 0;


        for (Entry entry : entryList) {
            switch (entry.getType()) {
                case REST:
                    int minutes = (int) entry.getDuration().toMinutes();
                    if (rest == 0 && minutes >= 15 && minutes < 45) rest = 15;
                    else if (rest == 0 && minutes >= 45) rest = 45;
                    else if (rest == 15 && minutes >= 30) rest += 30;
                    break;
                case DRIVE:
                    driving += (int) entry.getDuration().toMinutes();
                    break;
            }
            if (rest < 45 && driving > 270) return driving;
            if (rest >= 45) {
                driving = 0;
                rest = 0;
            }
        }

        return -1;
    }
}