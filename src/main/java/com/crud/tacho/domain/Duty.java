package com.crud.tacho.domain;

import com.crud.tacho.domain.decorator.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "DUTIES")
public class Duty implements Job, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "DUTY_ID")
    private Long dutyId;

    @Column
    private BigDecimal hourlyRate;

    @Column
    private BigDecimal allowance = BigDecimal.ZERO;

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

    public Duty(BigDecimal hourlyRate, BigDecimal allowance, String agency, String company) {
        this.hourlyRate = hourlyRate;
        this.allowance = allowance;
        this.agency = agency;
        this.company = company;
        //this.assignments = new HashSet<>();
    }
}
