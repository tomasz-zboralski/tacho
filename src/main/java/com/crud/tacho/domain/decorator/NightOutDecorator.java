package com.crud.tacho.domain.decorator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
//@Entity
//@DiscriminatorValue("N")
public class NightOutDecorator extends AbstractJobDecorator {

//    @Id
//    @GeneratedValue
//    @Column(name = "DUTY_ID")
//    private Long dutyId;

    BigDecimal value = new BigDecimal(25);

//
//
    public NightOutDecorator(Job job) {
        super(job);
    }

    @Override
    public BigDecimal getAllowance() {
        return super.getAllowance().add(value);
    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Id
//    public Long getId() {
//        return id;
//    }
}
