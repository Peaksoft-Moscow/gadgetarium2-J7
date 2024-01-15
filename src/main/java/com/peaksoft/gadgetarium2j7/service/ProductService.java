package com.peaksoft.gadgetarium2j7.service;
import com.peaksoft.gadgetarium2j7.model.dto.*;
import com.peaksoft.gadgetarium2j7.model.entities.Brand;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.mapper.ProductMapper;
import com.peaksoft.gadgetarium2j7.repository.BrandRepository;
import com.peaksoft.gadgetarium2j7.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    public ProductResponse create(ProductRequest productRequest) {
        Product product = productMapper.mapToEntity(productRequest);
        Brand brand = brandRepository.findByName(productRequest.getBrandName()).orElseThrow(() -> new EntityNotFoundException("Brand not found"));
        product.setBrandName(brand.getName());
        product.setBrand(brand);
        productRepository.save(product);
        return productMapper.mapToResponse(product);
    }

    public List<ProductResponse> getAll() {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productResponses.add(productMapper.mapToResponse(product));
        }
        return productResponses;
    }

    public void delete(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found"));
        productRepository.deleteById(productId);
    }

    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with id " + productId + " not found"));
        return productMapper.mapToResponse(product);
    }

    public SetPriceAndQuantityResponse setPriceAndQuantity(Long id, SetPriceAndQuantity updateRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        product.setTotalPrice(updateRequest.getTotalPrice());
        product.setQuantity(updateRequest.getQuantity());
        productRepository.save(product);
        return productMapper.mapToResponseUpdate(product);
    }

    public SetDescriptionResponse setDescription(Long id, SetDescription setDescription) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        product.setVideo(setDescription.getVideo());
        product.setPdf(setDescription.getPdf());
        product.setDescription(setDescription.getDescription());
        productRepository.save(product);
        return productMapper.mapToResponseSetDescription(product);

    }
}

