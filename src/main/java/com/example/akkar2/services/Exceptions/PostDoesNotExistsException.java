package com.example.akkar2.services.Exceptions;

public class PostDoesNotExistsException extends RuntimeException{
    public PostDoesNotExistsException(String m) {
        super(m);
    }
}
