package com.myapi.service.creditCard;

import com.myapi.infrastructure.exception.MyApiException;
import com.myapi.model.creditCard.dto.CreditCardListDTO;
import com.myapi.model.creditCard.dto.CreditCardPostDTO;
import com.myapi.model.creditCard.entity.CreditCard;
import com.myapi.model.creditCard.mapper.CreditCardMapper;
import com.myapi.repository.CreditCardRepository;
import com.myapi.service.creditCard.validation.CreditCardInsertValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardService {
    @Autowired
    CreditCardRepository creditCardRepository;

    private static MyApiException userNotFoundException() {
        MyApiException exception = new MyApiException();
        exception.addMessage("User not found");
        return exception;
    }

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

    public Page<CreditCardListDTO> listAll(Pageable pageable) {
        Page<CreditCard> result = creditCardRepository.findAll(pageable);
        List<CreditCardListDTO> items = result.getContent()
                .stream()
                .map(CreditCardMapper.INSTANCE::toCreditCardListDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(items, pageable, result.getTotalElements());
    }

    public CreditCardPostDTO findById(Long id) {
        return CreditCardMapper.INSTANCE.toCreditCardPostDTO(
                creditCardRepository.findById(id)
                        .orElseThrow(CreditCardService::userNotFoundException)
        );
    }
}
