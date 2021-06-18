package com.crud.tacho.mapper;

import com.crud.tacho.domain.Duty;
import com.crud.tacho.domain.DutyDto;
import com.crud.tacho.domain.decorator.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DutyMapper {

    public Duty mapJobToDuty(Job job) {
        return new Duty(
                job.getDutyId(),
                job.getHourlyRate(),
                job.getAllowance(),
                job.getAgency(),
                job.getCompany(),
                job.getAssignments()
        );

    }

//    public Duty mapToDuty(DutyDto dutyDto) {
//        return new Duty(
//                dutyDto.getDutyId(),
//                dutyDto.getHourlyRate(),
//                dutyDto.getAllowance(),
//                dutyDto.getAgency(),
//                dutyDto.getCompany(),
//                dutyDto.getAssignments()
//        );
//    }

    public DutyDto mapToDutyDto(Job job) {
        return new DutyDto(
                job.getDutyId(),
                job.getHourlyRate(),
                job.getAllowance(),
                job.getAgency(),
                job.getCompany(),
                job.getAssignments()
        );
    }

    public Set<DutyDto> mapToDutyDtoSet(Set<Duty> duties) {
        return duties.stream()
                .map(this::mapToDutyDto)
                .collect(Collectors.toSet());
    }

}
