package com.motorcompany.config.Exception;


public class SessionAlredyExistException extends RuntimeException {

    public SessionAlredyExistException(String message) {
        super(message);
    }
}
