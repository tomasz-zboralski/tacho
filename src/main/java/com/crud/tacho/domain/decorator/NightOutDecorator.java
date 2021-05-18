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
