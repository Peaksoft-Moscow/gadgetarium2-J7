package com.peaksoft.gadgetarium2j7.model.dto;

import com.peaksoft.gadgetarium2j7.model.entities.Category;
import com.peaksoft.gadgetarium2j7.model.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
public class ProductRequest {
    private Category category;
    private String brandName;
    private String guarantee;//гарантия
    private String name;
    private Memory memory;
    private String color;
    private String watchStraps;
    private String bodyMaterial;//материал корпуса
    private String smartWatchSize;
    private String DisplayDiagonal;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private WaterResistance waterResistance;
    private WirelessInterface wirelessInterface;
    private CaseShape caseShape;
    private String image;
    private OperationMemory operationMemory;
    private int simCard;



    private double priceFrom;
    private double priceTo;


    private OperationSystem operatingSystem;


}




