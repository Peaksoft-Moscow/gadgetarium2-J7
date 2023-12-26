package com.peaksoft.gadgetarium2j7.model.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SetPriceAndQuantity {
    private int totalPrice;
    private int quantity;
}
