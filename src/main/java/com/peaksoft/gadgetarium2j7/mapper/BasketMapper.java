package com.peaksoft.gadgetarium2j7.mapper;

import com.peaksoft.gadgetarium2j7.model.dto.BasketResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Basket;
import org.springframework.stereotype.Component;

@Component
public class BasketMapper {

    public BasketResponse mapToResponse(Basket basket) {
        BasketResponse basketResponse = new BasketResponse();
        basketResponse.setId(basket.getId());
        basketResponse.setAmount(basket.getAmount());
        basketResponse.setDiscount(basket.getDiscount());
        basketResponse.setTotal(basket.getTotal());
        basketResponse.setProductList(basket.getProductsz());
        return basketResponse;
    }
}
