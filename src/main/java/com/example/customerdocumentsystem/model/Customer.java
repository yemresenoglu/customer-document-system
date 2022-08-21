package com.example.customerdocumentsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Customer extends BaseModel {

    @Column(nullable = false, unique = true, length = 8)
    @Positive
    private Long customerNumber;

    @Column(nullable = false, unique = true, length = 11)
    @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$", message = "Invalid Turkish Identification Number!")
    private String idNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @Past(message = "Date of birthday must be before today.")
    private Date birthday;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private Set<CustomerAddress> customerAddresses = new HashSet<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerContact customerContact;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Document> documents = new HashSet<>();


    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    private String createdBy;

    private boolean isDeleted;


    public void addAddress(CustomerAddress customerAddress) {
        customerAddress.setCustomer(this);
        this.customerAddresses.add(customerAddress);
    }

    public void saveDocument(Document document) {
        document.setCustomer(this);
        this.documents.add(document);
    }


}
