package com.peaksoft.gadgetarium2j7.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.gadgetarium2j7.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Entity
@Table(name="products")
@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
     String name;
     int weight;
     String color;
     String brandName;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "brands_id")
     Brand brand;
     String screenSize;
    @Enumerated(EnumType.STRING)
     Memory memory;
     int simCard;
     String processor;
     String guarantee;
     double discount;
     LocalDate creatDate;
     String rating;
     double price;
     String watchband;
     String theMaterialOfTheCase;
     String smartWatchSize;
     LocalDate releaseDate;
     int article;
     String screen;
     int inStock;
     OperatingSystem operatingSystem;
     Gender gender;
     String image;
     WirelessInterface wirelessInterface;
     WaterResistance waterResistance;
     CaseShape caseShape;
     OperatingMemory operatingMemory;
     String video;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
     Category category;

    @Enumerated(EnumType.STRING)
     SubCategory subCategory;

}
