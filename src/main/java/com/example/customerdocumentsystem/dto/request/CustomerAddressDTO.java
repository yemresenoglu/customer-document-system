package com.example.customerdocumentsystem.dto.request;

import com.example.customerdocumentsystem.model.CustomerAddressType;

public record CustomerAddressDTO(String country,
                                 String city,
                                 String postalCode,
                                 String description,
                                 CustomerAddressType customerAddressType) {
}
