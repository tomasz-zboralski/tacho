package com.crud.tacho.domain;

import com.crud.tacho.domain.decorator.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
public class DutyDto implements Job {

    private Long dutyId;
    private BigDecimal hourlyRate;
    private BigDecimal allowance = BigDecimal.ZERO;
    private String agency;
    private String company;
    private Set<Assignment> assignments = new HashSet<>();

}
