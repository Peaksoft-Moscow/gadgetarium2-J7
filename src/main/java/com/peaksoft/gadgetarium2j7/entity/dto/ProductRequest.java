package com.peaksoft.gadgetarium2j7.entity.dto;
import com.peaksoft.gadgetarium2j7.entity.Brand;
import com.peaksoft.gadgetarium2j7.entity.Category;
import com.peaksoft.gadgetarium2j7.entity.enums.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductRequest {
    private String name;
    private Category category;
    private SubCategory subCategory;
    private String brandName;
    private String guarantee;//гарантия
    private Memory memory;
    private String color;
    private int article;
    private String screen;
    private OperatingSystem operatingSystem;
    private int simCard;
    private String processor;
    private int weight;
    private int inStock;
    private WirelessInterface wirelessInterface;
    private WaterResistance waterResistance;
    private String image;
    private int price;
    private CaseShape caseShape;
    private OperatingMemory operatingMemory;
    private String video;
}
