package com.example.customerdocumentsystem.converter;

import com.example.customerdocumentsystem.dto.request.SaveCustomerRequestDTO;
import com.example.customerdocumentsystem.model.Customer;

public interface CustomerServiceOperationConverter {

    Customer saveCustomer(SaveCustomerRequestDTO saveCustomerRequestDTO);
}
