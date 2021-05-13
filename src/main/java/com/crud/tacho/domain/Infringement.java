package com.crud.tacho.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@NamedQueries(
        @NamedQuery(
                name = "Infringement.retrieveValidInfringement",
                query = "FROM Infringement WHERE DATEDIFF(NOW(), START_TIME) < 28 "
        )
)
@Entity
@Table(name = "INFRINGEMENTS")
public class Infringement {

    @Id
    @GeneratedValue
    @Column(name = "INFRINGEMENT_ID")
    private Long infringementId;

    @Column
    private String type;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "ASSIGNMENT_ID")
    private Assignment assignment;

    @Autowired
    public Infringement(String type, LocalDateTime startTime, LocalDateTime endTime, Duration duration, Assignment assignment) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.assignment = assignment;
    }
}
