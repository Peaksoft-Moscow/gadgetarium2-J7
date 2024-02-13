package com.peaksoft.gadgetarium2j7.model.dto;

import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BasketResponse {
    private Long id;
    private int amount;// сумма
    private double discount;// скидка
    private int  quantity;// количество
    private double total; // итого
    private List<Product> productList;
    private User user;
}
