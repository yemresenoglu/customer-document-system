package com.example.customerdocumentsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
public class CustomerContact extends BaseModel {

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String callNumber;

    @OneToOne
    private Customer customer;
}
