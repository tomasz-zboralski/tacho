package com.crud.tacho.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "ENTRIES")
public class Entry {

    @Id
    @GeneratedValue
    @Column(name = "ENTRY_ID")
    private Long entryId;

    @Column
    private String type;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private Duration duration;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ASSIGNMENT_ID")
    private  Assignment assignment;

    public Entry(String type, LocalDateTime startTime, LocalDateTime endTime, Duration duration, Assignment assignment) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.assignment = assignment;
    }
}
