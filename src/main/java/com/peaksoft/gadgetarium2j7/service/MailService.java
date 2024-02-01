package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.exception.EntityNotFoundException;
import com.peaksoft.gadgetarium2j7.exception.MailSendingException;
import com.peaksoft.gadgetarium2j7.exception.PasswordsDoNotMatchException;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Random;
@Service
@RequiredArgsConstructor
public class MailService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    public void sendPasswordResetCode(String email) throws EntityNotFoundException {
    int pinCode = generatePinCode();
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setFrom("ajzirektoktosunova853@gmail.com");
    message.setSubject("Password reset");
    message.setText(String.valueOf(pinCode));
    try {
        javaMailSender.send(message);
        updateUserPinCode(email, pinCode);
    } catch (MailException e) {
        throw new MailSendingException("Не удалось отправить код для сброса пароля", e);
    }
}

    public String resetPassword(Principal principal ,String email, int pinCode, String newPassword, String confirmPassword)
            throws EntityNotFoundException, PasswordsDoNotMatchException {
        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException("Пароли не совпадают");
        } else {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
            if (principal.getName().equals(email)) {
                if (pinCode == user.getPinCode()) {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return "Сброс пароля прошел успешно";
                }
            }
            return "Неверный PIN-код";
        }
    }
    private int generatePinCode() {
        Random random = new Random();
        return random.nextInt(100000, 1000000);
    }

    private void updateUserPinCode(String email, int pinCode) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        user.setPinCode(pinCode);
        userRepository.save(user);
    }

}
