package com.peaksoft.gadgetarium2j7.entity.dto;
import com.peaksoft.gadgetarium2j7.entity.Brand;
import com.peaksoft.gadgetarium2j7.entity.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
@Builder
public class ProductResponse {
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Memory memory;
    private Brand brand;
    private String color;
    private OperatingMemory operatingMemory;
    private String rating;
    private int article;
    private  String screen;
    private OperatingSystem operatingSystem;
    private LocalDate releaseDate;
    private int simCard;
    private String  warranty;
    private int weight;
    private int inStock;
   private double price;
   private String processor;
}
