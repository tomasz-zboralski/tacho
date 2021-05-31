package com.crud.tacho.domain.decorator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class BonusDecorator extends AbstractJobDecorator {

    private BigDecimal value;

    public BonusDecorator(Job job, BigDecimal value) {
        super(job);
        this.value = value;
    }


    @Override
    public BigDecimal getAllowance() {
        return super.getAllowance().add(value);
    }
}
