package com.example.customerdocumentsystem.validator;

import com.example.customerdocumentsystem.dto.request.SaveCustomerRequestDTO;
import com.example.customerdocumentsystem.exception.BaseException;
import org.springframework.stereotype.Component;

@Component
public class SaveCustomerRequestDTOValidator implements Validator<SaveCustomerRequestDTO> {
    @Override
    public void validate(SaveCustomerRequestDTO input) throws BaseException {

        /**The method will be completed.*/
    }
}
