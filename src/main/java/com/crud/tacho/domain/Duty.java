package com.crud.tacho.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Duty {
    private BigDecimal rate;
    private String agency;
    private String company;
    private List<Assignment> assignments;
}
