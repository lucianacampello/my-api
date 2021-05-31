package com.myapi.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;

@Getter
@JsonTypeName("error")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class MessageErrorDTO {

    public MessageErrorDTO(String... message) {
        this.message = Arrays.asList(message);
    }

    @JsonAlias("message")
    private Collection<String> message;
}
