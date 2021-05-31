package com.crud.tacho.domain;

import com.crud.tacho.domain.decorator.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DutyDto implements Job {

    private Long dutyId;
    private BigDecimal hourlyRate;
    private BigDecimal allowance = BigDecimal.ZERO;
    private String agency;
    private String company;
    private Set<Assignment> assignments = new HashSet<>();

    public DutyDto(BigDecimal hourlyRate, BigDecimal allowance, String agency, String company) {
        this.hourlyRate = hourlyRate;
        this.allowance = allowance;
        this.agency = agency;
        this.company = company;
    }
}
