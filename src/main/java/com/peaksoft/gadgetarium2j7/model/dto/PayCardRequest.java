package com.peaksoft.gadgetarium2j7.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayCardRequest {
    private String ownerName;
    private int numberCard;
    private String mm;
    private String yy;
    private String cvc;
}
