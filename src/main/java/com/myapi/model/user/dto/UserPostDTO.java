package com.myapi.model.user.dto;

import com.myapi.model.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
    private String encryptedPassword;
}
