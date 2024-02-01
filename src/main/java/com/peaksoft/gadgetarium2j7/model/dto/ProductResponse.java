package com.peaksoft.gadgetarium2j7.model.dto;
import com.peaksoft.gadgetarium2j7.model.entities.Brand;
import com.peaksoft.gadgetarium2j7.model.enums.Memory;
import com.peaksoft.gadgetarium2j7.model.enums.OperationSystem;
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

    private Long id;
    private String brandName;
    private String article;
    private String rating;
    private int inStock;
    private int amount;
    private  String screen;
    private String color;
    private LocalDate releaseDate;
    @Enumerated(value = EnumType.STRING)
    private OperationSystem operatingSystem;
    @Enumerated(value = EnumType.STRING)
    private Memory memory;
    private int simCard;
    private String  warranty;
    private double price;
    private String processor;
    private int weight;
    private String name;
    private Brand brand;
    private int memorySize;

}
