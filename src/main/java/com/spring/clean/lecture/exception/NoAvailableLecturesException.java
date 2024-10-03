package com.spring.clean.lecture.exception;

public class NoAvailableLecturesException extends RuntimeException {
    public NoAvailableLecturesException(String message) {
        super(message);
    }
}