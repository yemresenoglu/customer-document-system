package com.example.customerdocumentsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User extends BaseModel {

    @Column(nullable = false, length = 11, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @Past(message = "Date of birthday must be before today.")
    private Date birthday;

    @Column(nullable = false)
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRole;
}
