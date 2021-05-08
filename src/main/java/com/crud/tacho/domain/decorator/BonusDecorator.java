package com.crud.tacho.domain.decorator;

import java.math.BigDecimal;

public class BonusDecorator extends AbstractJobDecorator {

    private BigDecimal value = new BigDecimal(50);

    public BonusDecorator(Job job) {
        super(job);
    }

    @Override
    public BigDecimal getAllowance() {
        return super.getAllowance().add(value);
    }

}
