package com.example.demo.exception;

public class PokeApiException extends RuntimeException {

    public PokeApiException(String message) {
        super(message);
    }
}
