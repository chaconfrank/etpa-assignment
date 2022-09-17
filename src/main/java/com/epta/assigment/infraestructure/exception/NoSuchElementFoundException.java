package com.epta.assigment.infraestructure.exception;

public class NoSuchElementFoundException extends RuntimeException {
    public NoSuchElementFoundException(String message){
        super(message);
    }
}
