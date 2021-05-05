package com.crud.tacho.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Entity
@Table(name = "DRIVERS")
public class Driver {

    @Id
    @Column(name = "DRIVER_ID")
    private Long id;

    @Column
    private String limitedCompanyName;

    @Column
    private String name;

    @Column
    private String surname;

    @OneToMany(
            targetEntity = Assignment.class,
            mappedBy = "driver",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Assignment> assignments;
}
