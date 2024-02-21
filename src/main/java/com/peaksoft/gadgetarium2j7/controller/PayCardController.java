package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.PayCardRequest;
import com.peaksoft.gadgetarium2j7.repository.PayCardRepository;
import com.peaksoft.gadgetarium2j7.service.PayCarderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PayCardController {

    private final PayCarderService payCarderService;


    @PostMapping("/cards")
    public ResponseEntity<String> saveCard(@RequestBody PayCardRequest payCardRequest) {
        try {
            payCarderService.saveCard(payCardRequest.getNumberCard(),
                    payCardRequest.getOwnerName(),
                    payCardRequest.getYy(),
                    payCardRequest.getMm(),
                    payCardRequest.getCvc());
            return ResponseEntity.ok("Карта успешно сохранена");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при сохранении карты");
        }
    }
}