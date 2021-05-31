package com.myapi.infrastructure.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class LoginException extends MyApiException {
    private static final long serialVersionUID = 5213724796532433495L;

    public static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    @Override
    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }
}
