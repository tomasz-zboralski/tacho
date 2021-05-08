package com.crud.tacho.domain.decorator;

import java.math.BigDecimal;

public class AbstractJobDecorator implements Job {

    private final Job job;

    protected AbstractJobDecorator(Job job) {
        this.job = job;
    }

    @Override
    public BigDecimal getAllowance() {
        return job.getAllowance();
    }
}
