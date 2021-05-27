package com.myapi.model.creditCard.dto;

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

    @NotBlank(message = "Number cannot be empty")
    @Size(min = 14, max = 16, message = "Number must have more than 14 and less than 16 characters")
//    @Min(value = 14, message = "Number must have more than 14 characters")
    private String number;

    @NotNull(message = "ExpirationDate cannot be null")
    private LocalDate expirationDate;

    @NotBlank(message = "CVV cannot be empty")
    @Size(min = 3, max = 3, message = "Number must have 3 characters")
    private String cvv;

    @NotNull(message = "userDto cannot be null")
    private Long user;
}
