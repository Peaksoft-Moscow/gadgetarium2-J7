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

    private Category category;
    private String categoryName;
    private String brandName;
    private String guarantee;//гарантия
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Memory memory;
    private String color;
    private String watchStraps;
    private String bodyMaterial;//материал корпуса
    private String smartWatchSize;
    private String DisplayDiagonal;
    @Enumerated(value = EnumType.STRING)
    private WaterResistance waterResistance;
    @Enumerated(value = EnumType.STRING)
    private WirelessInterface wirelessInterface;
    @Enumerated(value = EnumType.STRING)
    private CaseShape caseShape;
    private String image;
    @Enumerated(value = EnumType.STRING)
    private OperationMemory operationMemory;
    private int simCard;


}




