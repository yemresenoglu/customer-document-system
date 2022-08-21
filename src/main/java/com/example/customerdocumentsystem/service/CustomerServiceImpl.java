package com.example.customerdocumentsystem.service;

import com.example.customerdocumentsystem.converter.CustomerServiceOperationConverter;
import com.example.customerdocumentsystem.dto.request.CustomerUpdateRequestDTO;
import com.example.customerdocumentsystem.dto.request.SaveCustomerRequestDTO;
import com.example.customerdocumentsystem.dto.response.GetCustomerResponseDTO;
import com.example.customerdocumentsystem.exception.CustomerServiceOperationException;
import com.example.customerdocumentsystem.model.Customer;
import com.example.customerdocumentsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;


@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerServiceOperationConverter customerServiceOperationConverter;

    @Override
    @Transactional
    public void saveCustomer(SaveCustomerRequestDTO saveCustomerRequestDTO) {

        if (!Objects.isNull(customerRepository.findByIdNumber(saveCustomerRequestDTO.idNumber()))) {
            throw new CustomerServiceOperationException.CustomerExistException("There is customer in database by customer id number! " + saveCustomerRequestDTO.idNumber());
        }

        Customer customer = customerServiceOperationConverter.saveCustomer(saveCustomerRequestDTO);
        customerRepository.save(customer);
        log.info("Customer saved! " + saveCustomerRequestDTO.idNumber());
    }

    @Override
    public Collection<GetCustomerResponseDTO> getAllCustomers() {
        return customerRepository
                .findAllCustomersByDeleteStatusByJPQL(false)
                .stream()
                .map(customer -> new GetCustomerResponseDTO(customer.getIdNumber(), customer.getCustomerNumber(), customer.getName(), customer.getLastname(), customer.getBirthday(), customer.getGender(), customer.getCreatedAt(), customer.getCreatedBy()))
                .toList();
    }

    @Override
    @Transactional
    public void softDeleteCustomer(String idNumber, boolean softDelete) {


        if (Objects.isNull(customerRepository.findByIdNumber(idNumber))) {
            throw new CustomerServiceOperationException.CustomerNotFoundException("Customer not found! " + idNumber);
        }

        Customer customer = customerRepository.findByIdNumber(idNumber);

        if (customer.isDeleted() == true) {
            throw new CustomerServiceOperationException.CustomerAlreadyDeletedException("Customer is already soft deleted!" + idNumber);
        }

        if (softDelete) {

            customer.setDeleted(true);
            customerRepository.save(customer);
            log.info(idNumber + " customer is soft deleted.");
        }

    }

    @Override
    @Transactional
    public void hardDeleteCustomer(String idNumber, boolean hardDelete) {

        if (Objects.isNull(customerRepository.findByIdNumber(idNumber))) {
            throw new CustomerServiceOperationException.CustomerNotFoundException("Customer not found! " + idNumber);
        }

        Customer customer = customerRepository.findByIdNumber(idNumber);

        if (hardDelete == true) {
            customerRepository.delete(customer);
            log.info(customer.getIdNumber() + " customer is hard deleted!");
        }

    }

    @Override
    @Transactional
    public void updateCustomer(String idNumber, CustomerUpdateRequestDTO customerUpdateRequestDTO) {


        if (Objects.isNull(customerRepository.findByIdNumber(idNumber))) {
            throw new CustomerServiceOperationException.CustomerNotFoundException("Customer not found! " + idNumber);
        }

        Customer customer = customerRepository.findByIdNumber(idNumber);

        customer.setIdNumber(customerUpdateRequestDTO.idNumber());
        customer.setName(customerUpdateRequestDTO.name());
        customer.setLastname(customerUpdateRequestDTO.lastname());
        customerRepository.save(customer);

        log.info(idNumber + " customer is updated by " + customerUpdateRequestDTO.idNumber() + " infos");

    }
}
