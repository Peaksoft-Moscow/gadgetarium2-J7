package com.peaksoft.gadgetarium2j7.model.dto;

import com.peaksoft.gadgetarium2j7.model.enums.Memory;
import com.peaksoft.gadgetarium2j7.model.enums.OperationMemory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class SetPriceAndQuantityResponse {
    private String brandName;
    private String color;
    private Memory memory;
    private OperationMemory operationMemory;
    private int simCard;
    private LocalDate releaseDate;
    private int totalPrice;
    private int quantity;
}
