package com.myapi.infrastructure.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MyApiException extends RuntimeException {
    private static final long serialVersionUID = -7361547397325571524L;

    private final List<String> messages = new ArrayList<>();
    public static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }

    public void addMessage(final String message) {
        this.messages.add(message);
    }

    public boolean hasMessage() {
        return !this.messages.isEmpty();
    }
}
