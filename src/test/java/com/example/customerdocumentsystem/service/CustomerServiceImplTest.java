package com.example.customerdocumentsystem.service;


import com.example.customerdocumentsystem.converter.CustomerServiceOperationConverter;
import com.example.customerdocumentsystem.exception.CustomerServiceOperationException;
import com.example.customerdocumentsystem.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CustomerServiceImplTest {

    private CustomerRepository customerRepository;
    private CustomerServiceOperationConverter customerServiceOperationConverter;
    private CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerServiceOperationConverter = mock(CustomerServiceOperationConverter.class);
        customerServiceImpl = new CustomerServiceImpl(customerRepository, customerServiceOperationConverter);
    }

    @Test
    public void testFindByCustomerIdNumber_whenCustomerIdNumberDoesNotExist_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findByIdNumber("idNumber")).thenReturn(null);

        assertThrows(CustomerServiceOperationException.CustomerNotFoundException.class, () -> customerServiceImpl.softDeleteCustomer("idNumber", true));

    }


}
