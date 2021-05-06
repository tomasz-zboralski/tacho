package com.crud.tacho.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity(name = "DUTIES")
public class Duty {

    @Id
    @GeneratedValue
    @Column(name = "DUTY_ID")
    private Long dutyId;

    @Column
    private BigDecimal rate;

    @Column
    private String agency;

    @Column
    private String company;

    @OneToMany(
            targetEntity = Assignment.class,
            mappedBy = "duty",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Assignment> assignments = new HashSet<>();

    public Duty(BigDecimal rate, String agency, String company, Set<Assignment> assignments) {
        this.rate = rate;
        this.agency = agency;
        this.company = company;
        this.assignments = assignments;
    }
}
