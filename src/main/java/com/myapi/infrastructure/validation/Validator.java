package com.myapi.infrastructure.validation;

import com.myapi.infrastructure.exception.MyApiException;

public interface Validator {
    void execute() throws MyApiException;
}
