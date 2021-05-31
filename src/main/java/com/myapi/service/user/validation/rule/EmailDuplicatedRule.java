package com.myapi.service.user.validation.rule;

import com.myapi.infrastructure.exception.MyApiException;
import com.myapi.infrastructure.validation.BusinessRule;
import com.myapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDuplicatedRule implements BusinessRule {
    private UserRepository userRepository;
    private String email;

    @Override
    public void verify(MyApiException exception) {
        if (userRepository.existsByEmail(email)) {
            exception.addMessage("Already exists a user registered with this email");
            throw exception;
        }
    }
}
