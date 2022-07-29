package com.example.hospital.exception;

public class PeopleNotFoundException extends RuntimeException{
    public PeopleNotFoundException(String msg) {
        super(msg);
    }
}
