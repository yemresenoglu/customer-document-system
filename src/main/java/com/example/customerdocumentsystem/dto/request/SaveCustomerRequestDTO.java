package com.example.customerdocumentsystem.dto.request;

import com.example.customerdocumentsystem.model.Gender;

import java.util.Date;

public record SaveCustomerRequestDTO(Long customerNumber,
                                     String idNumber,
                                     String name,
                                     String lastname,
                                     Date birthday,
                                     Gender gender,
                                     CustomerAddressDTO customerAddress,
                                     CustomerContactDTO customerContact) {
}
