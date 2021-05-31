package com.myapi.service.creditCard.validation.rule;

import com.myapi.infrastructure.exception.MyApiException;
import com.myapi.infrastructure.validation.BusinessRule;
import com.myapi.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardDuplicatedNumberRule implements BusinessRule {
    private CreditCardRepository creditCardRepository;
    private String number;

    @Override
    public void verify(MyApiException exception) {
        if (creditCardRepository.existsByNumber(number)) {
            exception.addMessage("Already exists a credit card registered with this number");
        }
    }
}
