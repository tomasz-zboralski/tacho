package com.crud.tacho.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Entity
@Table(name = "ASSIGNMENTS")
public class Assignment {

    @Id
    @Column(name = "ASSIGNMENT_ID")
    private Long id;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "END_TIME")
    private Date endTime;

    @Column(name = "DURATION")
    private Duration duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DUTY_ID")
    private Duty duty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DRIVER_ID")
    private Driver driver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoice;

    @OneToMany(
            targetEntity = Entry.class,
            mappedBy = "assignment",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Entry> entries;

    @OneToMany(
            targetEntity = Infringement.class,
            mappedBy = "assignment",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Infringement> infringements;
}
