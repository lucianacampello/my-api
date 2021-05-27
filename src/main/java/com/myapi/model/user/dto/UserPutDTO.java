package com.myapi.model.user.dto;

import com.myapi.model.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPutDTO {
    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 150, message = "Name cannot be over 150 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email not valid")
    @Size(max = 50, message = "Email cannot be over 50 characters")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Size(max = 13, message = "Role cannot be over 13 characters")
    private Role role;
}
