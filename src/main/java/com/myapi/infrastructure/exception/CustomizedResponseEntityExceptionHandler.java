package com.myapi.infrastructure.exception;

import com.myapi.infrastructure.dto.MessageErrorDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler(MyApiException.class)
    public ResponseEntity<MessageErrorDTO> handleMyException(final MyApiException e) {
        LOGGER.error(String.valueOf(e));
        return ResponseEntity.status(MyApiException.HTTP_STATUS).body(formatMessageError(e));
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<MessageErrorDTO> handleLoginException(final LoginException e) {
        LOGGER.error(String.valueOf(e));
        return ResponseEntity.status(LoginException.HTTP_STATUS).body(formatMessageError(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageErrorDTO> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        LOGGER.error(String.valueOf(e));
        return ResponseEntity.status(MyApiException.HTTP_STATUS).body(formatMessageErrorFromValidation(e));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageErrorDTO> handleBadCredentialsException(final BadCredentialsException e) {
        LOGGER.error(String.valueOf(e));
        return ResponseEntity.status(LoginException.HTTP_STATUS).body(new MessageErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageErrorDTO> handleException(final Exception e) {
        LOGGER.error(String.valueOf(e));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorDTO("Unexpected Error!"));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageErrorDTO> handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        LOGGER.error(String.valueOf(e));
        Throwable cause = e.getCause();
        String error = "Integrity Data error:";
        if (cause instanceof ConstraintViolationException) {
            error += "Constraint Violations " + ((ConstraintViolationException) cause).getConstraintName();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorDTO(error));
    }

    private MessageErrorDTO formatMessageError(final MyApiException e) {
        return new MessageErrorDTO(e.getMessages().toArray(new String[0]));
    }

    private MessageErrorDTO formatMessageErrorFromValidation(MethodArgumentNotValidException e) {
        String[] messages = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toArray(String[]::new);
        return new MessageErrorDTO(messages);
    }
}
