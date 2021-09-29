package com.kevin.activitytracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActivityNotFoundException extends RuntimeException {

    private static final Long serialVersionUID = 43657875645323534L;

    public ActivityNotFoundException() {
        this("Activity not found");
    }

    public ActivityNotFoundException(String message) {
        this(message, null);
    }

    public ActivityNotFoundException(String message, Throwable cause) {
        super(message, null);
    }
    
}
