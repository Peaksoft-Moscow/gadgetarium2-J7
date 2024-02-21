package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.model.entities.PayCard;
import com.peaksoft.gadgetarium2j7.repository.PayCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayCarderService {

    private final PayCardRepository payCardRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public void saveCard(int numberCard, String cardNumber, String cardHolderName, String cvv, String cvc) {

        int hashedCardNumber = Integer.parseInt(passwordEncoder.encode(cardNumber));
        String hashedCvv = passwordEncoder.encode(cvv);


        PayCard card = new PayCard();
        card.setNumberCard(hashedCardNumber);
        card.setOwnerName(cardHolderName);
        card.setYy(card.getYy());
        card.setMm(card.getMm());
        card.setCvc(hashedCvv);

        payCardRepository.save(card);
    }
}
