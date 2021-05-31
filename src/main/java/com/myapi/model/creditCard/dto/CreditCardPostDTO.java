package com.myapi.model.creditCard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Credit card number, must have between 14 and 16 digits", example = "1111222233334444")
    private String number;

    @NotNull(message = "ExpirationDate cannot be null")
    @Schema(description = "Credit card expiration date")
    private LocalDate expirationDate;

    @NotBlank(message = "CVV cannot be empty")
    @Size(min = 3, max = 3, message = "Number must have 3 characters")
    @Schema(description = "Credit card CVV number")
    private String cvv;

    @NotNull(message = "userDto cannot be null")
    @Schema(description = "The user's id who belong this credit card")
    private Long user;
}
