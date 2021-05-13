package com.crud.tacho.mapper;

import com.crud.tacho.domain.Infringement;
import com.crud.tacho.domain.InfringementDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfringementMapper {
    InfringementDto mapToInfringementDto(Infringement infringement) {
        return new InfringementDto(
                infringement.getInfringementId(),
                infringement.getType(),
                infringement.getStartTime(),
                infringement.getEndTime(),
                infringement.getDuration(),
                infringement.getAssignment()
        );
    }
    public List<InfringementDto> mapToInfringementDtoList(List<Infringement> infringementList) {
        return infringementList.stream()
                .map(this::mapToInfringementDto)
                .collect(Collectors.toList());
    }
}
