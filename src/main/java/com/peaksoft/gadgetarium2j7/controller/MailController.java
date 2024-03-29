package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.exception.EntityNotFoundException;
import com.peaksoft.gadgetarium2j7.exception.PasswordsDoNotMatchException;
import com.peaksoft.gadgetarium2j7.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/newsletter")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @GetMapping("/sendPasswordResetCode")
    public void sendPasswordResetCode(@RequestParam String email) throws EntityNotFoundException {
        mailService.sendPasswordResetCode(email);
    }

    @PostMapping("/resetPassword")
    public String resetPassword(Principal principal, @RequestParam String email, @RequestParam int pinCode,
                                @RequestParam String newPassword, @RequestParam String confirmPassword)
            throws EntityNotFoundException, PasswordsDoNotMatchException {
        return mailService.resetPassword(principal,email, pinCode, newPassword, confirmPassword);
    }
}
