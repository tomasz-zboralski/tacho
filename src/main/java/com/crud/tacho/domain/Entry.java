package com.crud.tacho.domain;

import lombok.Getter;

import java.time.Duration;
import java.util.Date;

@Getter
public class Entry {
    private String type;
    private Date startTime;
    private Date endTime;
    private Duration duration;
    private Assignment assignment;
}
