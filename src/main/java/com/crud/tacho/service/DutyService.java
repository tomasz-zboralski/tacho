package com.crud.tacho.service;

import com.crud.tacho.domain.Duty;
import com.crud.tacho.domain.DutyDto;
import com.crud.tacho.domain.decorator.BonusDecorator;
import com.crud.tacho.domain.decorator.Job;
import com.crud.tacho.domain.decorator.NightOutDecorator;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.mapper.DutyMapper;
import com.crud.tacho.repository.DutyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class DutyService {

    private final DutyMapper dutyMapper;
    private final DutyRepository dutyRepository;

    public Duty getDutyById(Long dutyId) throws DutyNotFoundException {
        return dutyRepository.findById(dutyId)
                        .orElseThrow(DutyNotFoundException::new);

    }
    public Duty createDuty(Duty duty) {
        return dutyRepository.save(duty);
    }

    public Duty addNightOut(Long dutyId) throws DutyNotFoundException {
        Job duty = getDutyById(dutyId);
        duty = new NightOutDecorator(duty);
        return dutyMapper.mapJobToDuty(duty);

    }

    public Duty addBonus(Long dutyId, BigDecimal value) throws DutyNotFoundException {
        Job duty = getDutyById(dutyId);
        duty = new BonusDecorator(duty, value);
        return dutyMapper.mapJobToDuty(duty);
    }

    public Duty addHoliday(Long dutyId) throws DutyNotFoundException {
        Job duty = getDutyById(dutyId);
        duty = new NightOutDecorator(duty);
        return dutyRepository.save(dutyMapper.mapJobToDuty(duty));
    }




}
