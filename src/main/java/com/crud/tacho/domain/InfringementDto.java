package com.crud.tacho.domain;

import lombok.AllArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
public class InfringementDto {

    private Long infringementId;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    private Assignment assignment;

}
