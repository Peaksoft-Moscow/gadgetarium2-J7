package com.peaksoft.gadgetarium2j7.exception;

import org.springframework.mail.MailException;

public class MailSendingException extends RuntimeException {
    public MailSendingException(String string, MailException e) {
    }
}
