package com.jumbochips.chillguy.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class InvalidJwtException extends RuntimeException {
    private final String message;
    private final HttpStatus status;

}
