package com.peaksoft.gadgetarium2j7.service;
import com.peaksoft.gadgetarium2j7.model.dto.*;
import com.peaksoft.gadgetarium2j7.model.entities.Brand;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.mapper.ProductMapper;
import com.peaksoft.gadgetarium2j7.model.entities.SpecialProducts;
import com.peaksoft.gadgetarium2j7.repository.BrandRepository;
import com.peaksoft.gadgetarium2j7.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public SpecialProductsResponse getSpecialProducts(){
        List<Product> allProducts = productRepository.findAll();

        class RecommendedProductsComparator implements Comparator<Product>{
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o1.getRating() - o2.getRating());
            }
        }
        Comparator<Product> RecommendedProductsComparatorObject = new RecommendedProductsComparator();
        allProducts.sort(RecommendedProductsComparatorObject);
        List<Product> RecommendedProducts = new ArrayList<>();
        RecommendedProducts.add(allProducts.get(allProducts.size()-1));
        RecommendedProducts.add(allProducts.get(allProducts.size()-2));
        RecommendedProducts.add(allProducts.get(allProducts.size()-3));
        RecommendedProducts.add(allProducts.get(allProducts.size()-4));
        RecommendedProducts.add(allProducts.get(allProducts.size()-5));
        RecommendedProducts.add(allProducts.get(allProducts.size()-6));
        SpecialProducts specialProducts = new SpecialProducts();
        specialProducts.setRecommendedProducts(RecommendedProducts);

        class DiscountProductsComparator implements Comparator<Product> {

            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o1.getDiscount() - o2.getDiscount());
            }
        }
        Comparator<Product> DiscountProductComparatorObject = new DiscountProductsComparator();
        allProducts.sort(DiscountProductComparatorObject);
        List<Product> DiscountProducts = new ArrayList<>();
        DiscountProducts.add(allProducts.get(allProducts.size()-1));
        DiscountProducts.add(allProducts.get(allProducts.size()-2));
        DiscountProducts.add(allProducts.get(allProducts.size()-3));
        DiscountProducts.add(allProducts.get(allProducts.size()-4));
        DiscountProducts.add(allProducts.get(allProducts.size()-5));
        DiscountProducts.add(allProducts.get(allProducts.size()-6));

        specialProducts.setDiscountProducts(DiscountProducts);

        class NewProductsComparator implements Comparator<Product> {

            @Override
            public int compare(Product o1, Product o2) {
                return o1.getCreatDate().compareTo(o2.getCreatDate());
            }
        }
        Comparator<Product> NewProductComparatorObject = new NewProductsComparator();
        allProducts.sort(NewProductComparatorObject);
        List<Product> NewProducts = new ArrayList<>();
        NewProducts.add(allProducts.get(allProducts.size()-1));
        NewProducts.add(allProducts.get(allProducts.size()-2));
        NewProducts.add(allProducts.get(allProducts.size()-3));
        NewProducts.add(allProducts.get(allProducts.size()-4));
        NewProducts.add(allProducts.get(allProducts.size()-5));
        NewProducts.add(allProducts.get(allProducts.size()-6));

        specialProducts.setNewProducts(NewProducts);

        return productMapper.mapToResponses(specialProducts);
    }
}

