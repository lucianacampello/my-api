package com.myapi.model.creditCard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardListDTO {
    private Long id;
    private String number;
    private LocalDate expirationDate;
}
