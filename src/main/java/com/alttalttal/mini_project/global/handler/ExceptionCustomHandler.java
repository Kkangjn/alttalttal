package com.alttalttal.mini_project.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.alttalttal.mini_project.global.dto.MessageResponseDto;

@RestControllerAdvice
public class ExceptionCustomHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<MessageResponseDto> handlerException(IllegalArgumentException ex) {
        MessageResponseDto restApiException = new MessageResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }

}
