package com.peaksoft.gadgetarium2j7.model.dto;


import com.peaksoft.gadgetarium2j7.model.entities.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SpecialProductsResponse {
    private Long id;
    private String name;
    private List<Product> discountProducts;
    private List<Product> newProducts;
    private List<Product> recommendedProducts;
}