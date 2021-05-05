package com.crud.tacho.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Getter
@Entity
@Table(name = "INFRINGEMENTS")
public class Infringement {

    @Id
    @Column(name = "INFRINGEMENT_ID")
    private Long id;

    @Column
    private String type;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "ASSIGNMENT_ID")
    private Assignment assignment;
}
