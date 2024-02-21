package com.peaksoft.gadgetarium2j7.mapper;

import com.peaksoft.gadgetarium2j7.model.dto.BasketResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Basket;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

@Component
public class BasketMapper {

    public BasketResponse mapToResponse(Basket basket) {
        BasketResponse basketResponse = new BasketResponse();
        basketResponse.setId(basket.getId());
        basketResponse.setAmount(basket.getAmount());
        basketResponse.setDiscount(basket.getDiscount());
        basketResponse.setTotal(basket.getTotal());
        basketResponse.setProductList(basket.getProducts());
        return basketResponse;
    }
    public Basket mapToBasket(BasketResponse basketResponse) {
        Basket basket = new Basket();
        basket.setId(basketResponse.getId());
        basket.setTotal(basketResponse.getTotal());
        return basket;
    }
}
