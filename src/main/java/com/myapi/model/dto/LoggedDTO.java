package com.myapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoggedDTO {
    private String token;
    private Long id;
    private String name;
    private String email;
    private String role;
}
