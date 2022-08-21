package com.example.customerdocumentsystem.controller;

import com.example.customerdocumentsystem.dto.request.CustomerUpdateRequestDTO;
import com.example.customerdocumentsystem.dto.request.SaveCustomerRequestDTO;
import com.example.customerdocumentsystem.dto.response.GetCustomerResponseDTO;
import com.example.customerdocumentsystem.service.CustomerService;
import com.example.customerdocumentsystem.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final Validator<SaveCustomerRequestDTO> saveCustomerRequestDTOValidator;
    private final Validator<String> customerIDNumberValidator;


    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody SaveCustomerRequestDTO saveCustomerRequestDTO) {

        saveCustomerRequestDTOValidator.validate(saveCustomerRequestDTO);
        customerService.saveCustomer(saveCustomerRequestDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<GetCustomerResponseDTO>> getCustomers() {


        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{idNumber}")
    public ResponseEntity<?> softDeleteCustomer(@PathVariable String idNumber,
                                                @RequestParam(name = "softDelete") boolean softDelete) {


        customerIDNumberValidator.validate(idNumber);
        customerService.softDeleteCustomer(idNumber, softDelete);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idNumber}")
    public ResponseEntity<?> hardDeleteCustomer(@PathVariable String idNumber,
                                                @RequestParam(name = "hardDelete") boolean hardDelete) {


        customerIDNumberValidator.validate(idNumber);
        customerService.hardDeleteCustomer(idNumber, hardDelete);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{idNumber}/updateInfo")
    public ResponseEntity<?> updateCustomerInfo(@PathVariable String idNumber, CustomerUpdateRequestDTO customerUpdateRequestDTO) {

        customerIDNumberValidator.validate(idNumber);
        customerService.updateCustomer(idNumber, customerUpdateRequestDTO);

        return ResponseEntity.ok().build();
    }
}
