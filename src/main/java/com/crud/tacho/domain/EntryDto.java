package com.crud.tacho.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
public class EntryDto {

    private Long entryId;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    //private Long assignmentId;
    private Assignment assignment;
}
