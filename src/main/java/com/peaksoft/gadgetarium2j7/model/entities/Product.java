package com.peaksoft.gadgetarium2j7.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peaksoft.gadgetarium2j7.model.enums.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    int weight;
    String color;
    String categoryName;
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
    int article;
    String screen;
    int inStock;
    @Enumerated(value=EnumType.STRING)
    OperationSystem operatingSystem;
    @Enumerated(value=EnumType.STRING)
    Gender gender;
    String image;
    @Enumerated(value=EnumType.STRING)
    WirelessInterface wirelessInterface;
    @Enumerated(value= EnumType.STRING)
    WaterResistance waterResistance;
    @Enumerated(value= EnumType.STRING)
    CaseShape caseShape;
    String video;
    String pdf;
    String description;
    int totalPrice;
    int quantity;
    @Enumerated(value= EnumType.STRING)
    SubCategory subCategory;
    private String watchStraps;
    @Enumerated(value= EnumType.STRING)
    OperationMemory operationMemory;
    String bodyMaterial;
    String DisplayDiagonal;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_orders", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orderList = new ArrayList<>();

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH},mappedBy ="product" )
    private Comment comment;

}
