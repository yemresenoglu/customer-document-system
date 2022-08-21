package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.dto.request.CustomerUpdateRequestDTO;
import com.example.customerdocumentsystem.dto.request.SaveCustomerRequestDTO;
import com.example.customerdocumentsystem.dto.response.GetCustomerResponseDTO;

import java.util.Collection;

public interface CustomerService {
    void saveCustomer(SaveCustomerRequestDTO saveCustomerRequestDTO);

    Collection<GetCustomerResponseDTO> getAllCustomers();

    void softDeleteCustomer(String idNumber, boolean softDelete);

    void hardDeleteCustomer(String idNumber, boolean hardDelete);

    void updateCustomer(String idNumber, CustomerUpdateRequestDTO customerUpdateRequestDTO);
}
