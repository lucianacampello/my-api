package com.myapi.infrastructure.validation;

import com.myapi.infrastructure.exception.MyApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidatorImpl implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorImpl.class);

    private final List<BusinessRule> businessRules = new ArrayList<>();

    protected void addRule(BusinessRule rule) {
        businessRules.add(rule);
    }

    public ValidatorImpl() {
        logger.info("entrou no validator impl");
    }

    @Override
    public void execute() throws MyApiException {
        MyApiException exception = new MyApiException();

        businessRules.forEach(rule -> rule.verify(exception));

        if (exception.hasMessage()) {
            logger.error(exception.getMessages().stream().collect(Collectors.joining(System.lineSeparator())));
            throw exception;
        }
    }
}
