package com.crud.tacho.domain;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class Invoice {
    private Date date;
    private boolean paid;
    private List<Assignment> assignments;
}
