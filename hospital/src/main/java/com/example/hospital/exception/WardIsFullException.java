package com.example.hospital.exception;

public class WardIsFullException extends RuntimeException{
    public WardIsFullException (String msg) {
        super(msg);
    }
}
