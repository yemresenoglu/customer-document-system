package com.example.customerdocumentsystem.validator;

import com.example.customerdocumentsystem.exception.BaseException;
import com.example.customerdocumentsystem.exception.CustomerServiceOperationException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class CustomerIdNumberValidator implements Validator<String> {

    @Override
    public void validate(String customerIdNumber) throws BaseException {

        if (Objects.isNull(customerIdNumber)) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Customer id number can not be empty or null");
        }
        if (!checkCustomerIdNumberLetter(customerIdNumber)) {
            throw new CustomerServiceOperationException.CustomerNotValidException("The customer id number characters must be digit!");
        }
        if (!checkCustomerİdNumberRegex(customerIdNumber)) {
            throw new CustomerServiceOperationException.CustomerNotValidException("Invalid Turkish Identification Number!");
        }
    }

    private boolean checkCustomerIdNumberLetter(String customerIdNumber) {

        for (int i = 0; i < customerIdNumber.length(); i++) {
            if (!Character.isDigit(customerIdNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCustomerİdNumberRegex(String customerIdNumber) {
        String regexPattern = "^[1-9]{1}[0-9]{9}[02468]{1}$";

        return Pattern.compile(regexPattern)
                .matcher(customerIdNumber)
                .matches();
    }
}
