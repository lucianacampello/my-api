package com.myapi.model.creditCard.dto;

import com.myapi.model.user.dto.UserPutDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardPostDTO {
    @NotBlank
    @Size(min = 14, max = 16)
    private String number;

    @NotNull
    private LocalDate expirationDate;

    @NotNull
    @Size(min = 3, max = 3)
    private String cvv;

    @NotNull
    private UserPutDTO userDto;
}
