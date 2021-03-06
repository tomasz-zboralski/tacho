package com.crud.tacho.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "HOLIDAY")
    private boolean isHoliday;

    @Column(name = "DURATION")
    private Duration duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DUTY_ID")
    private Duty duty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DRIVER_ID")
    private Driver driver;

    @JsonManagedReference
    @OneToMany(
            targetEntity = Entry.class,
            mappedBy = "assignment",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Entry> entries = new ArrayList<>();

    @OneToMany(
            targetEntity = Infringement.class,
            mappedBy = "assignment",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Infringement> infringements;

    //@Autowired
    public Assignment(LocalDateTime startTime, LocalDateTime endTime, Duty duty, Driver driver) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.duty = duty;
        this.driver = driver;
        this.duration = Duration.between(startTime, endTime);
        this.isHoliday = false;
    }

    @Autowired
    public Assignment(Duty duty) {
        this.duty = duty;
    }
}
