package com.peaksoft.gadgetarium2j7.exception;

import org.springframework.mail.MailException;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
