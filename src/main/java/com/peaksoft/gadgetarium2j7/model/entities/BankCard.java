package com.peaksoft.gadgetarium2j7.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "bank_card")
@Getter
@Setter
@NoArgsConstructor
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

}
