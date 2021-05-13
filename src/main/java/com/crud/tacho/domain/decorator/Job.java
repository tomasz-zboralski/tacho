package com.crud.tacho.domain.decorator;

import com.crud.tacho.domain.Assignment;

import java.math.BigDecimal;
import java.util.Set;

public interface Job {
    Long getDutyId();
    BigDecimal getHourlyRate();
    BigDecimal getAllowance();
    String getAgency();
    String getCompany();
    Set<Assignment> getAssignments();
}
