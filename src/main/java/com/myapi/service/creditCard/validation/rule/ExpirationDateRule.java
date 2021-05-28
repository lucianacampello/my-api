package com.myapi.service.creditCard.validation.rule;

import com.myapi.infrastructure.exception.MyApiException;
import com.myapi.infrastructure.validation.BusinessRule;
import com.myapi.infrastructure.validation.defaultRules.DateCompareDefaultRule;

import java.time.LocalDate;

public class ExpirationDateRule extends DateCompareDefaultRule implements BusinessRule {

    public ExpirationDateRule(LocalDate date) {
        super(date, LocalDate.now());
    }

    @Override
    public void verify(MyApiException exception) {
        if (isBefore()) {
            exception.addMessage("Credit card date has expired");
        }
    }
}
