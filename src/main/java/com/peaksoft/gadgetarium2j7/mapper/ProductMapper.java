package com.peaksoft.gadgetarium2j7.mapper;
import com.peaksoft.gadgetarium2j7.entity.Product;
import com.peaksoft.gadgetarium2j7.entity.dto.ProductRequest;
import com.peaksoft.gadgetarium2j7.entity.dto.ProductResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductMapper {
    public Product mapToEntity(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setSubCategory(productRequest.getSubCategory());
//        product.setBrand(productRequest.getBrand());
        product.setGuarantee(productRequest.getGuarantee());
        product.setMemory(productRequest.getMemory());
        product.setColor(productRequest.getColor());
        product.setArticle(productRequest.getArticle());
        product.setScreen(productRequest.getScreen());
        product.setOperatingSystem(productRequest.getOperatingSystem());
        product.setSimCard(productRequest.getSimCard());
        product.setProcessor(productRequest.getProcessor());
        product.setWeight(productRequest.getWeight());
        product.setInStock(productRequest.getInStock());
        product.setWirelessInterface(productRequest.getWirelessInterface());
        product.setWaterResistance(productRequest.getWaterResistance());
        product.setImage(productRequest.getImage());
        product.setPrice(productRequest.getPrice());
        product.setCaseShape(productRequest.getCaseShape());
        product.setOperatingMemory(productRequest.getOperatingMemory());
        product.setVideo(product.getVideo());
        return product;
    }
     public ProductResponse mapToResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .brand(product.getBrand())
                .memory(product.getMemory())
                .color(product.getColor())
                .operatingMemory(product.getOperatingMemory())
                .rating(product.getRating())
                .article(product.getArticle())
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
}
