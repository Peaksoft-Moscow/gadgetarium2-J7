package com.peaksoft.gadgetarium2j7.model.dto;

import com.peaksoft.gadgetarium2j7.model.entities.Category;
import com.peaksoft.gadgetarium2j7.model.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Builder
public class ProductRequest {

    private String name;
    private Category categoryName;
    private String brandName;
    private String guarantee;//гарантия
    @Enumerated(EnumType.STRING)
    private Memory memory;
    private String color;
    private String watchStraps;
    private String bodyMaterial;//материал корпуса
    private String smartWatchSize;
    private String DisplayDiagonal;
    @Enumerated(value = EnumType.STRING)
    private WaterResistance waterResistance;
    @Enumerated(EnumType.STRING)
    private WirelessInterface wirelessInterface;
    private CaseShape caseShape;
    private String image;
    @Enumerated(EnumType.STRING)
    private OperationMemory operationMemory;
    private int simCard;
}