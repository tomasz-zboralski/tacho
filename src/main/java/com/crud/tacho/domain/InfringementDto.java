package com.crud.tacho.domain;

import lombok.AllArgsConstructor;

import java.time.Duration;
import java.util.Date;

@AllArgsConstructor
public class InfringementDto {

    private Long infringementId;
    private String type;
    private Date startTime;
    private Date endTime;
    private Duration duration;
    private Assignment assignment;

}
