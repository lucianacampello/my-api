package com.myapi.controller;

import com.myapi.infrastructure.dto.MessageResponseDTO;
import com.myapi.infrastructure.exception.MyApiException;
import com.myapi.model.creditCard.dto.CreditCardListDTO;
import com.myapi.model.creditCard.dto.CreditCardPostDTO;
import com.myapi.service.creditCard.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {
    @Autowired
    private CreditCardService service;

    @PostMapping
    @Operation(summary = "Create the credit card", tags = {"Credit Card"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "When has validations errors")
    })
    public ResponseEntity<MessageResponseDTO> create(@Valid @RequestBody CreditCardPostDTO dto) throws MyApiException {
        service.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponseDTO(Collections.singletonMap("Success",
                        Collections.singletonList("Credit card successfully created")))
        );
    }

    @GetMapping
    @Operation(summary = "List all credits card paginated",
            description = "The default size is 20, use the parameter size to change the default value",
            tags = {"Credit Card"})
    public ResponseEntity<Page<CreditCardListDTO>> findAllPageable(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.listAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a credits card by id",
            tags = {"Credit Card"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Credit Card founded"),
            @ApiResponse(responseCode = "400", description = "When credit card is not found")
    })
    public ResponseEntity<CreditCardPostDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
