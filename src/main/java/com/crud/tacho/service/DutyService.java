package com.crud.tacho.service;

import com.crud.tacho.domain.Duty;
import com.crud.tacho.domain.DutyDto;
import com.crud.tacho.domain.decorator.Job;
import com.crud.tacho.exception.DutyNotFoundException;
import com.crud.tacho.mapper.DutyMapper;
import com.crud.tacho.repository.DutyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DutyService {

    //private final DutyMapper dutyMapper;
    private final DutyRepository dutyRepository;

    public Duty getDutyById(Long dutyId) throws DutyNotFoundException {
        return dutyRepository.findById(dutyId)
                        .orElseThrow(DutyNotFoundException::new);

    }
    public Duty createDuty(Duty duty) {
        return dutyRepository.save(duty);
    }


}
