package com.myapi.model.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninDTO {

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email not valid")
    @Size(max = 50, message = "Email cannot be over 50 characters")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
