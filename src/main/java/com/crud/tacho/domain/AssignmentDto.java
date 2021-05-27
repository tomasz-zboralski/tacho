package com.crud.tacho.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDto {
    private Long assignmentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isHoliday;
    private Duration duration;
    private Duty duty;
    private Driver driver;
    private Invoice invoice;
    private List<EntryDto> entries;
    private Set<Infringement> infringements;
}
