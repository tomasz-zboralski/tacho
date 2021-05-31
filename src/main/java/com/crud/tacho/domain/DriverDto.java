package com.crud.tacho.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {

    private Long driverId;
    private String limitedCompanyName;
    private String name;
    private String surname;
    private Set<AssignmentDto> assignments;

    public DriverDto(Long driverId, String name) {
        this.driverId = driverId;
        this.name = name;
    }
}
