package com.peaksoft.gadgetarium2j7.mapper;
import com.peaksoft.gadgetarium2j7.model.dto.*;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.model.entities.SpecialProducts;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class ProductMapper {
    public Product mapToEntity(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setSubCategory(productRequest.getCategory().getSubCategory());
        product.setGuarantee(productRequest.getGuarantee());
        product.setMemory(productRequest.getMemory());
        product.setColor(productRequest.getColor());
        product.setCreatDate(LocalDate.now());
        product.setGender(productRequest.getGender());
        product.setSimCard(productRequest.getSimCard());
        product.setWirelessInterface(productRequest.getWirelessInterface());
        product.setWaterResistance(productRequest.getWaterResistance());
        product.setImage(productRequest.getImage());
        product.setOperationMemory(productRequest.getOperationMemory());
        product.setCaseShape(productRequest.getCaseShape());
        return product;
    }

     public ProductResponse mapToResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .brandName(product.getBrandName())
                .memory(product.getMemory())
                .color(product.getColor())
                .rating(product.getRating())
                .screen(product.getScreen())
                .operatingSystem(product.getOperatingSystem())
                .releaseDate(LocalDate.now())
                .simCard(product.getSimCard())
                .warranty(product.getGuarantee())
                .weight(product.getWeight())
                .inStock(product.getInStock())
                .price(product.getPrice())
                .processor(product.getProcessor())
                .build();
    }

    public SetPriceAndQuantityResponse mapToResponseUpdate(Product product){
        return SetPriceAndQuantityResponse.builder()
                .brandName(product.getBrandName())
                .color(product.getColor())
                .memory(product.getMemory())
                .operationMemory(product.getOperationMemory())
                .simCard(product.getSimCard())
                .totalPrice(product.getTotalPrice())
                .quantity(product.getQuantity())
                .build();

    }

    public SetDescriptionResponse mapToResponseSetDescription(Product product){
        return SetDescriptionResponse.builder()
                .video(product.getVideo())
                .pdf(product.getPdf())
                .description(product.getDescription())
                .build();
    }

    public SpecialProducts mapToEntities(SpecialProductsRequest request){
        SpecialProducts specialProducts = new SpecialProducts();
        specialProducts.setName(request.getName());
        return specialProducts;
    }

    public SpecialProductsResponse mapToResponses(SpecialProducts specialProducts){
        return SpecialProductsResponse.builder()
                .name(specialProducts.getName())
                .newProducts(specialProducts.getNewProducts())
                .id(specialProducts.getId())
                .recommendedProducts(specialProducts.getRecommendedProducts())
                .discountProducts(specialProducts.getDiscountProducts())
                .build();
    }
}
