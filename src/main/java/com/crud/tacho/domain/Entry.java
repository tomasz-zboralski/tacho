package com.crud.tacho.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENTRIES")
public class Entry {

    @Id
    @GeneratedValue
    @Column(name = "ENTRY_ID")
    private Long entryId;

    @Enumerated(EnumType.STRING)
    @Column
    private EntryType type;

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

    public Entry(EntryType type, LocalDateTime startTime, LocalDateTime endTime) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
    }
}
