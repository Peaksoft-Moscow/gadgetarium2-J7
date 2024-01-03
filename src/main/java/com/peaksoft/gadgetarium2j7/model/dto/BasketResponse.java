package com.peaksoft.gadgetarium2j7.model.dto;

import com.peaksoft.gadgetarium2j7.model.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BasketResponse {
    private Long id;
    private int amount;// сумма обшая
    private double discount;// скидка
    private int  quantity;// количество
    private double total; // итого
    private List<Product> productList;
}
