package fr.polytech.jydet.td5.exception;

import lombok.Getter;

@Getter
public class ServiceException extends Exception {

    private String error;

    public ServiceException(String message) {
        super(message);
        this.error = message;
    }
}
