package com.example.customerdocumentsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.util.Date;

@Getter
@Setter
@Entity
public class Document extends BaseModel {

    @Column(nullable = false, unique = true, length = 8)
    @Positive
    private String documentNumber;

    @Column(nullable = false, unique = true)
    String name;

    @Lob
    @Column(name = "content", nullable = false)
    String content;

    @Column(nullable = false)
    private String type;

    @ManyToOne()
    Customer customer;

    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    @PastOrPresent
    private Date uploadAt;

    @Column
    private String uploadBy;

    @Transient
    public String getCustomerFullName() {
        return this.customer.getName() + " " + this.getCustomer().getName();
    }


}
