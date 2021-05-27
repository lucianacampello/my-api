package com.myapi.model.user.dto;

import com.myapi.model.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPutDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
