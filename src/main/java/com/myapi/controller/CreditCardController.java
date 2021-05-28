package com.myapi.controller;

import com.myapi.infrastructure.dto.MessageResponseDTO;
import com.myapi.infrastructure.exception.MyApiException;
import com.myapi.model.creditCard.dto.CreditCardListDTO;
import com.myapi.model.creditCard.dto.CreditCardPostDTO;
import com.myapi.service.creditCard.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {
    @Autowired
    private CreditCardService service;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> create(@Valid @RequestBody CreditCardPostDTO dto) throws MyApiException {
        MessageResponseDTO messageDto = service.create(dto);

        if (messageDto != null) {
            ResponseEntity.badRequest().body(messageDto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponseDTO(Collections.singletonMap("Success",
                        Collections.singletonList("Credit card successfully created")))
        );
    }

    @GetMapping
    public ResponseEntity<List<CreditCardListDTO>> getAll() {
        return ResponseEntity.ok(service.list());
    }
}
