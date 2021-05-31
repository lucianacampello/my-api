package com.myapi.model.creditCard.mapper;

import com.myapi.model.creditCard.dto.CreditCardListDTO;
import com.myapi.model.creditCard.dto.CreditCardPostDTO;
import com.myapi.model.creditCard.entity.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CreditCardMapper {
    public static final CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    @Mapping(source = "user", target = "user.id")
    public abstract CreditCard toCreditCard(CreditCardPostDTO creditCardPostDTO);

    @Mapping(source = "user.id", target = "user")
    public abstract CreditCardPostDTO toCreditCardPostDTO(CreditCard creditCard);

    public abstract CreditCardListDTO toCreditCardListDTO(CreditCard creditCard);
}
