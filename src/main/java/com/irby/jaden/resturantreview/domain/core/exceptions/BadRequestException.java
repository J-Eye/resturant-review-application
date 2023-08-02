package com.irby.jaden.resturantreview.domain.core.exceptions;

public class BadRequestException extends Exception{
    public BadRequestException(String msg){
        super(msg);
    }
}
