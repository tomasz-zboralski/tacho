package com.crud.tacho.domain.decorator;

import com.crud.tacho.domain.Assignment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
//@Getter
//@Entity
//@DiscriminatorValue("A")
public abstract class AbstractJobDecorator implements Job {



//    @Id
//    @GeneratedValue
//    @Column(name = "DUTY_ID")
//    private Long id;

    private Job job;

    public AbstractJobDecorator(Job job) {
        this.job = job;
    }

    @Override
    public Long getDutyId() {
        return job.getDutyId();
    };

    @Override
    public BigDecimal getHourlyRate() {
        return job.getHourlyRate();
    };

    @Override
    public BigDecimal getAllowance() {
        return job.getAllowance();
    }

    @Override
    public String getAgency() {
        return job.getAgency();
    };

    @Override
    public String getCompany() {
        return job.getCompany();
    };

    @Override
    public Set<Assignment> getAssignments() {
        return job.getAssignments();
    };

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Id
//    public Long getId() {
//        return id;
//    }
}
