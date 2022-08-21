package com.example.customerdocumentsystem.converter;

import com.example.customerdocumentsystem.dto.request.SaveCustomerRequestDTO;
import com.example.customerdocumentsystem.generator.CustomerNumberGenerator;
import com.example.customerdocumentsystem.model.Customer;
import com.example.customerdocumentsystem.model.CustomerAddress;
import com.example.customerdocumentsystem.model.CustomerContact;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerServiceOperationConverterImpl implements CustomerServiceOperationConverter {


    @Override
    public Customer saveCustomer(SaveCustomerRequestDTO saveCustomerRequestDTO) {

        Customer customer = new Customer();

        customer.setIdNumber(saveCustomerRequestDTO.idNumber());
        customer.setCustomerNumber(CustomerNumberGenerator.customerNumberGenerator(8));
        customer.setName(saveCustomerRequestDTO.name());
        customer.setLastname(saveCustomerRequestDTO.lastname());
        customer.setBirthday(saveCustomerRequestDTO.birthday());
        customer.setGender(saveCustomerRequestDTO.gender());
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("Yunus Emre Şenoğlu");

        CustomerAddress customerAddress = new CustomerAddress();

        customerAddress.setCountry(saveCustomerRequestDTO.customerAddress().country());
        customerAddress.setCity(saveCustomerRequestDTO.customerAddress().city());
        customerAddress.setPostalCode(saveCustomerRequestDTO.customerAddress().postalCode());
        customerAddress.setDescription(saveCustomerRequestDTO.customerAddress().description());
        customerAddress.setCustomerAddressType(saveCustomerRequestDTO.customerAddress().customerAddressType());
        customerAddress.setCustomer(customer);

        customer.addAddress(customerAddress);

        CustomerContact customerContact = new CustomerContact();

        customerContact.setEmail(saveCustomerRequestDTO.customerContact().email());
        customerContact.setCallNumber(saveCustomerRequestDTO.customerContact().callNumber());
        customerContact.setCustomer(customer);

        customer.setCustomerContact(customerContact);

        return customer;
    }
}
