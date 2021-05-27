package com.myapi.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListDTO {
    public static final String CLASS_PATH = "com.myapi.model.user.dto.UserListDTO";

    private String name;
    private String email;
    private Long creditCardsAmount;
}
