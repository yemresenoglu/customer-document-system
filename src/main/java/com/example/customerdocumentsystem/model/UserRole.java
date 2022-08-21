package com.example.customerdocumentsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class UserRole extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(length = 150)
    private String description;

    @OneToMany(mappedBy = "userRole")
    private Set<User> userSet;
}
