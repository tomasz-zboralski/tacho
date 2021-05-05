package com.crud.tacho.domain;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Entity(name = "DUTIES")
public class Duty {

    @Id
    @Column(name = "DUTY_ID")
    private Long id;

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
    private Set<Assignment> assignments;
}
