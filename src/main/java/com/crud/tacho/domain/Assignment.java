package com.crud.tacho.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "ASSIGNMENTS")
public class Assignment {

    @Id
    @GeneratedValue
    @Column(name = "ASSIGNMENTS_ID")
    private Long assignmentId;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "END_TIME")
    private LocalDateTime endTime;

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

//    public Assignment(LocalDateTime startTime, LocalDateTime endTime, Duty duty, Driver driver, Invoice invoice, List<Entry> entries, Set<Infringement> infringements) {
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.duty = duty;
//        this.driver = driver;
//        this.invoice = invoice;
//        this.entries = entries;
//        this.infringements = infringements;
//        //this.duration = Duration.between(endTime, startTime);
//    }

    public Assignment(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
