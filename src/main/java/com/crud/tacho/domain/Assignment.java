package com.crud.tacho.domain;

import lombok.Getter;
import javax.persistence.*;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "ASSIGNMENTS")
public class Assignment {

    @Id
    private Long id;
    @Column(name = "START_TIME")
    private Date startTime;
    @Column(name = "END_TIME")
    private Date endTime;
    @Column(name = "DURATION")
    private Duration duration;
//    private Duty duty;
//    private Driver driver;
//    private Invoice invoice;
//    private List<Entry> entries;
}
