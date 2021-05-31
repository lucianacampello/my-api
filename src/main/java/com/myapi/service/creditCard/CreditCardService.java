package com.myapi.service.creditCard;

import com.myapi.model.creditCard.dto.CreditCardListDTO;
import com.myapi.model.creditCard.dto.CreditCardPostDTO;
import com.myapi.model.creditCard.mapper.CreditCardMapper;
import com.myapi.repository.CreditCardRepository;
import com.myapi.service.creditCard.validation.CreditCardInsertValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardService {
    @Autowired
    CreditCardRepository creditCardRepository;

    @Transactional(rollbackFor = Throwable.class)
    public void create(CreditCardPostDTO dto) {
        new CreditCardInsertValidate(dto, creditCardRepository).execute();
        creditCardRepository.save(CreditCardMapper.INSTANCE.toCreditCard(dto));
    }

    @Transactional(readOnly = true)
    public List<CreditCardListDTO> list() {
        return creditCardRepository.findAll()
                .stream()
                .map(CreditCardMapper.INSTANCE::toCreditCardListDTO)
                .collect(Collectors.toList());
    }
}
