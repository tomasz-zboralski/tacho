package com.crud.tacho.mapper;

import com.crud.tacho.domain.Driver;
import com.crud.tacho.domain.DriverDto;
import com.crud.tacho.exception.AssignmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DriverMapper {

    private final AssignmentMapper assignmentMapper;

    public Driver mapToDriver(DriverDto driverDto) throws AssignmentNotFoundException {
        return new Driver(
                driverDto.getDriverId(),
                driverDto.getLimitedCompanyName(),
                driverDto.getName(),
                driverDto.getSurname(),
                assignmentMapper.mapToAssignmentSet(driverDto.getAssignments())
                );
    }

    public DriverDto mapToDriverDto(Driver driver) {
        return new DriverDto(
                driver.getDriverId(),
                driver.getLimitedCompanyName(),
                driver.getName(),
                driver.getSurname(),
                assignmentMapper.mapToAssignmentDtoSet(driver.getAssignments())
        );
    }

}
