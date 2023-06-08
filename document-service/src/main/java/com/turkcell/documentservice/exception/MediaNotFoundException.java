package com.turkcell.documentservice.exception;

public class MediaNotFoundException extends RuntimeException{

    public MediaNotFoundException(String message){
        super(message);
    }
}
