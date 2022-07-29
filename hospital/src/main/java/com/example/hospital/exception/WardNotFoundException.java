package com.example.hospital.exception;

public class WardNotFoundException extends RuntimeException{
    public WardNotFoundException(String msg) {
        super(msg);
    }
}
