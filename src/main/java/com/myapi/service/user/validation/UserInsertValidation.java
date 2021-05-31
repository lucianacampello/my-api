package com.myapi.service.user.validation;

import com.myapi.infrastructure.validation.ValidatorImpl;
import com.myapi.model.user.dto.UserPostDTO;
import com.myapi.repository.UserRepository;
import com.myapi.service.user.validation.rule.EmailDuplicatedRule;

public class UserInsertValidation extends ValidatorImpl {
    public UserInsertValidation(UserPostDTO dto, UserRepository userRepository) {
        addRule(new EmailDuplicatedRule(userRepository, dto.getEmail()));
    }
}
