package com.crud.tacho.domain.decorator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
//@Entity
//@DiscriminatorValue("B")
public class BonusDecorator extends AbstractJobDecorator {

//    @Id
//    @GeneratedValue
//    @Column(name = "DUTY_ID")
//    private Long dutyId;

    private BigDecimal value; //= new BigDecimal(50);

    public BonusDecorator(Job job, BigDecimal value) {
        super(job);
        this.value = value;
        //this.dutyId = job.getDutyId();
    }


    @Override
    public BigDecimal getAllowance() {
        return super.getAllowance().add(value);
    }

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Id
//    public Long getId() {
//        return id;
//    }
}
