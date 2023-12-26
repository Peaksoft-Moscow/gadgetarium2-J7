package com.peaksoft.gadgetarium2j7.mapper;

import com.peaksoft.gadgetarium2j7.model.dto.BrandRequest;
import com.peaksoft.gadgetarium2j7.model.dto.BrandResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public Brand mapToEntity(BrandRequest brandRequest) {
        Brand brand = new Brand();
        brand.setName(brandRequest.getName());
        brand.setImage(brandRequest.getImage());
        return brand;
    }

    public BrandResponse mapToResponse(Brand brand) {
        return BrandResponse.builder()
                .name(brand.getName())
                .image(brand.getImage())
                .build();
    }
}
