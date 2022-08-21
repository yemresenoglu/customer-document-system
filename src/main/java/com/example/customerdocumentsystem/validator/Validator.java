package com.example.customerdocumentsystem.validator;

import com.example.customerdocumentsystem.exception.BaseException;

public interface Validator <T>{

    void validate(T input) throws BaseException;
}
