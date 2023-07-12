package com.adobe.round2.exceptionhandler;

/*
 *  This class is a custom generated exception class especially for situations when jakarta validation fails
 */
public class ValidationFailedException extends RuntimeException{
    public ValidationFailedException(String message) {
        super(message);
    }
}
