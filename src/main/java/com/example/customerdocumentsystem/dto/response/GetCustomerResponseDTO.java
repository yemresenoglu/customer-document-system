package com.example.customerdocumentsystem.dto.response;

import com.example.customerdocumentsystem.model.Gender;

import java.util.Date;

public record GetCustomerResponseDTO(String idNumber,
                                     Long customerNumber,
                                     String name,
                                     String lastname,
                                     Date birthday,
                                     Gender gender,
                                     Date createdAt,
                                     String createdBy) {
}
