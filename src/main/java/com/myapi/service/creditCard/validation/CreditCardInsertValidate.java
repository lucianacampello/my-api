package com.myapi.service.creditCard.validation;

import com.myapi.infrastructure.validation.ValidatorImpl;
import com.myapi.model.creditCard.dto.CreditCardPostDTO;
import com.myapi.repository.CreditCardRepository;
import com.myapi.service.creditCard.validation.rule.CreditCardDuplicatedNumberRule;
import com.myapi.service.creditCard.validation.rule.CreditCardNumberRule;
import com.myapi.service.creditCard.validation.rule.ExpirationDateRule;

public class CreditCardInsertValidate extends ValidatorImpl {

    public CreditCardInsertValidate(CreditCardPostDTO dto, CreditCardRepository repository) {
        addRule(new CreditCardNumberRule(dto.getNumber()));
        addRule(new ExpirationDateRule(dto.getExpirationDate()));
        addRule(new CreditCardDuplicatedNumberRule(repository, dto.getNumber()));
    }
}
