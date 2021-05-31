package com.crud.tacho.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EntryDto {

    private Long entryId;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    //private Long assignmentId;
    private Assignment assignment;

    public EntryDto(Long entryId, String type, LocalDateTime startTime, LocalDateTime endTime) {
        this.entryId = entryId;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
