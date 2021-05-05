package com.crud.tacho.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Driver {
    private String limitedCompanyName;
    private String name;
    private String surname;
    private List<Assignment> assignments;
}
