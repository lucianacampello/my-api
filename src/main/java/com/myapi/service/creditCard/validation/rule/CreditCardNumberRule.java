package com.myapi.service.creditCard.validation.rule;

import br.com.moip.validators.CreditCard;
import com.myapi.infrastructure.exception.MyApiException;
import com.myapi.infrastructure.validation.BusinessRule;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardNumberRule implements BusinessRule {
    private String number;

    @Override
    public void verify(MyApiException exception) {
        if (!new CreditCard(number).isValid()) {
            exception.addMessage("Credit card number not valid");
        }
    }
}
