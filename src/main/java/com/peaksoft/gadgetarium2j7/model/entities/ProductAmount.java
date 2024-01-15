package com.peaksoft.gadgetarium2j7.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productAmount")
@Getter
@Setter
@NoArgsConstructor
public class ProductAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private int amount; //количество
    private double price; //цена товара
    private int totalPrice; //общая цена
    @ManyToOne(cascade = CascadeType.ALL)
    private Basket basket;



}
