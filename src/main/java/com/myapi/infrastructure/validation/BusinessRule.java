package com.myapi.infrastructure.validation;

import com.myapi.infrastructure.exception.MyApiException;

public interface BusinessRule {
    void verify(MyApiException exception);
}
