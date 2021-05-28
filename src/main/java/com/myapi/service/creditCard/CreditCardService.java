package com.myapi.service.creditCard;

import com.myapi.infrastructure.dto.MessageResponseDTO;
import com.myapi.model.creditCard.dto.CreditCardListDTO;
import com.myapi.model.creditCard.dto.CreditCardPostDTO;
import com.myapi.model.creditCard.mapper.CreditCardMapper;
import com.myapi.repository.CreditCardRepository;
import com.myapi.service.creditCard.validation.CreditCardInsertValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardService {
    @Autowired
    CreditCardRepository creditCardRepository;

    @Transactional(rollbackFor = Throwable.class)
    public MessageResponseDTO create(CreditCardPostDTO dto) {
        //TODO throw business exception
        new CreditCardInsertValidate(dto).execute();

        MessageResponseDTO messageDto = null;

        if (creditCardRepository.existsByNumber(dto.getNumber())) {
            messageDto = new MessageResponseDTO(
                    Collections.singletonMap(
                            "error",
                            Collections.singletonList("Already exists a credit card registered with this number"))
            );
        } else {
            creditCardRepository.save(CreditCardMapper.INSTANCE.toCreditCard(dto));
        }
        return messageDto;
    }

    @Transactional(readOnly = true)
    public List<CreditCardListDTO> list() {
        return creditCardRepository.findAll()
                .stream()
                .map(CreditCardMapper.INSTANCE::toCreditCardListDTO)
                .collect(Collectors.toList());
    }
}
