package com.crud.tacho.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "DRIVERS")
public class Driver {

    @Id
    @GeneratedValue
    @Column(name = "DRIVER_ID")
    private Long driverId;

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
    private Set<Assignment> assignments = new HashSet<>();

    public Driver(String limitedCompanyName, String name, String surname, Set<Assignment> assignments) {
        this.limitedCompanyName = limitedCompanyName;
        this.name = name;
        this.surname = surname;
        this.assignments = assignments;
    }
}
