package com.peaksoft.gadgetarium2j7.model.dto;
import com.peaksoft.gadgetarium2j7.model.entities.Brand;
import com.peaksoft.gadgetarium2j7.model.entities.Category;
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
    private String name;
    private String brandName;
    private Category categoryName;
    private String article;
    private String rating;
    private int inStock;
    private int amount;
    private  String screen;
    private String color;
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    private OperationSystem operatingSystem;
    @Enumerated(EnumType.STRING)
    private Memory memory;
    private int simCard;
    private String  warranty;
    private double price;
    private String processor;
    private int weight;
    private Brand brand;

    private int memorySize;

}
