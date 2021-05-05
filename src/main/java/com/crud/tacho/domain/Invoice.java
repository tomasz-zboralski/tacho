package com.crud.tacho.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Entity
@Table(name = "INVOICES")
public class Invoice {

    @Id
    @Column(name = "INVOICE_ID")
    private Long id;

    @Column
    private Date date;

    @Column
    private boolean paid;

    @OneToMany(
            targetEntity = Assignment.class,
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Assignment> assignments;
}
