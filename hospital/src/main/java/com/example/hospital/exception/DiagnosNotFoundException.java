package com.example.hospital.exception;

public class DiagnosNotFoundException extends RuntimeException{
    public DiagnosNotFoundException(String msg) {
        super(msg);
    }
}
