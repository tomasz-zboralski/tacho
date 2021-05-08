package com.crud.tacho.domain.decorator;

import java.math.BigDecimal;

public class NightOutDecorator extends AbstractJobDecorator {

    BigDecimal value = new BigDecimal(25);

    public NightOutDecorator(Job job) {
        super(job);
    }

    @Override
    public BigDecimal getAllowance() {
        return super.getAllowance().add(value);
    }

}
