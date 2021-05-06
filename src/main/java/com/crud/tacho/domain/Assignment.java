package com.crud.tacho.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ASSIGNMENTS")
public class Assignment {

    @Id
    @GeneratedValue
    @Column(name = "ASSIGNMENT_ID")
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

    @Autowired
    public Assignment(LocalDateTime startTime, LocalDateTime endTime, Duty duty, Driver driver) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duty = duty;
        this.driver = driver;
        this.duration = Duration.between(startTime, endTime);
    }

    public Assignment(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(endTime, startTime);
    }
}
