package com.crud.tacho.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

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
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private Duration duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ASSIGNMENT_ID")
    private Assignment assignment;
}
