package com.myapi.model.dto;

import com.myapi.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {
    private String name;
    private String email;
    private String password;
    private Perfil role;
}
