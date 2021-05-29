package com.haulmont.creditProccesor.Exceptions;

public class CreditProcessorException extends Exception{
    public CreditProcessorException(String message) {
        super(message);
    }

    public CreditProcessorException(String message, Throwable cause) {
        super(message, cause);
    }
}
