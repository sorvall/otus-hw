package ru.sorokin.exception;

public class CustomOrmRuntimeException extends RuntimeException{
    public CustomOrmRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
