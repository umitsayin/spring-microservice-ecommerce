package com.turkcell.commonservice.config.exception;

public class EntityAlreadyException extends RuntimeException{
    public EntityAlreadyException(String message){
        super(message);
    }
}
