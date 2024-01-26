package com.peaksoft.gadgetarium2j7.service;
import com.peaksoft.gadgetarium2j7.model.dto.*;
import com.peaksoft.gadgetarium2j7.model.entities.Brand;
import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.mapper.ProductMapper;
import com.peaksoft.gadgetarium2j7.model.entities.User;
import com.peaksoft.gadgetarium2j7.repository.BrandRepository;
import com.peaksoft.gadgetarium2j7.repository.ProductRepository;
import com.peaksoft.gadgetarium2j7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

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

//    public List<ProductResponse> compare_product(Long id, Principal principal) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
//        User user = userRepository.findByEmail(principal.getName())
//                .orElseThrow(() -> new EntityNotFoundException("User with id " + principal.getName() + " not found"));
//        user.setProducts((List<Product>) product);
//        List<Product> productList = new ArrayList<>();
//            productList.add(productRepository.getAllByProductId(product.getId()));
//        return getResponse(productList);
//    }
//
//    public List<ProductResponse> getProductByCategory(String category, boolean difference, Principal principal) {
//        List<Product> products = new ArrayList<>();
//        User user = userRepository.findByEmail(principal.getName())
//                .orElseThrow(() -> new EntityNotFoundException("User with id " + principal.getName() + " not found"));
//        List<Product> getProduct = productRepository.getProductByCategory(user.getId(), category);
//        if (difference) {
//            for (int i = 1; i < getProduct.size(); i++) {
//                if (!getProduct.get(i).getBrand().equals(getProduct.get(i - 1).getBrand()) ||
//                        !getProduct.get(i).getScreen().equals(getProduct.get(i - 1).getScreen()) ||
//                        !getProduct.get(i).getColor().equals(getProduct.get(i - 1).getColor()) ||
//                        !getProduct.get(i).getOperatingSystem().equals(getProduct.get(i - 1).getOperatingSystem()) ||
//                        !getProduct.get(i).getMemory().equals(getProduct.get(i - 1).getMemory())  ||
//                        !(getProduct.get(i).getWeight() == getProduct.get(i - 1).getWeight()) ||
//                        !(getProduct.get(i).getSimCard()==(getProduct.get(i - 1).getSimCard()))) {
//                    products.add(getProduct.get(i));
//                }
//            }
//            return getResponse(products);
//        }
//        return getResponse(getProduct);
//    }
//
//    // в этом методе  из класса Product  получаем ResponseProduct.
//    public List<ProductResponse> getResponse(List<Product> products) {
//        List<ProductResponse> responses = new ArrayList<>();
//        for (Product product : products) {
//            responses.add(productMapper.mapToResponse(product));
//        }
//        return responses;
//    }
//
//    public void deleteProductInCompare(Principal principal) {
//        User user = userRepository.findByEmail(principal.getName())
//                .orElseThrow(() -> new EntityNotFoundException("User with id " + principal.getName() + " not found"));
//        List<Product> productList = user.getProducts();
//        List<Product> products = new ArrayList<>();
//        for (Product product : productList) {
//            products.add(productRepository.getAllByProductId(product.getId()));
//            for (Product product1 : products) {
//                productRepository.deleteById(product1.getId());
//            }
//        }
//    }
//
//    public List<ProductResponse> compare_product(Long id, Principal principal) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
//        User user = userRepository.findByEmail(principal.getName())
//                .orElseThrow(() -> new EntityNotFoundException("User with id " + principal.getName() + " not found"));
//        user.setProducts((List<Product>) product);
//        List<Product> productList = new ArrayList<>();
//            productList.add(productRepository.getAllByProductId(product.getId()));
//        return getResponse(productList);
//    }
//
//    public List<ProductResponse> searchAndPaginationProduct(String category,
//                                                                double min_price,
//                                                                double max_price, String color,
//                                                                String operationMemory,
//                                                                String operationSystem, int page, int size, Principal principal) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        User user = userRepository.findByEmail(principal.getName())
//                .orElseThrow(() -> new EntityNotFoundException("User with id " + principal.getName() + " not found"));
//        List<Product> productList = user.getProducts();
//        category = category == null ? "" : category;
//        min_price = min_price < 0 ? 0 : min_price;
//        max_price = max_price == 0 ? Double.MAX_VALUE : max_price;
//        color = color == null ? "" : color;
//        operationMemory = operationMemory == null ? "" : operationMemory;
//        operationSystem = operationSystem == null ? "" : operationSystem;
//        List<Product> products = productRepository.getAllProductByFilter(category, min_price, max_price, color,
//                OperationMemory.valueOf(operationMemory), OperationSystem.valueOf(operationSystem), pageable);
//        return getResponse(products);
//    }
//
//    public List<ProductResponse> getProductByCategory(String category, boolean difference, Principal principal) {
//        List<Product> products = new ArrayList<>();
//        User user = userRepository.findByEmail(principal.getName())
//                .orElseThrow(() -> new EntityNotFoundException("User with id " + principal.getName() + " not found"));
//        List<Product> getProduct = productRepository.getProductByCategory(user.getId(), category);
//        if (difference) {
//            for (int i = 1; i < getProduct.size(); i++) {
//                if (!getProduct.get(i).getBrand().equals(getProduct.get(i - 1).getBrand()) ||
//                        !getProduct.get(i).getScreen().equals(getProduct.get(i - 1).getScreen()) ||
//                        !getProduct.get(i).getColor().equals(getProduct.get(i - 1).getColor()) ||
//                        !getProduct.get(i).getOperatingSystem().equals(getProduct.get(i - 1).getOperatingSystem()) ||
//                        !getProduct.get(i).getMemory().equals(getProduct.get(i - 1).getMemory())  ||
//                        !(getProduct.get(i).getWeight() == getProduct.get(i - 1).getWeight()) ||
//                        !(getProduct.get(i).getSimCard()==(getProduct.get(i - 1).getSimCard()))) {
//                    products.add(getProduct.get(i));
//                }
//            }
//            return getResponse(products);
//        }
//        return getResponse(getProduct);
//    }
//
//    // в этом методе  из класса Product  получаем ResponseProduct.
//    public List<ProductResponse> getResponse(List<Product> products) {
//        List<ProductResponse> responses = new ArrayList<>();
//        for (Product product : products) {
//            responses.add(productMapper.mapToResponse(product));
//        }
//        return responses;
//    }
//
//    public void deleteProductInCompare(Principal principal) {
//        User user = userRepository.findByEmail(principal.getName())
//                .orElseThrow(() -> new EntityNotFoundException("User with id " + principal.getName() + " not found"));
//        List<Product> productList = user.getProducts();
//        List<Product> products = new ArrayList<>();
//        for (Product product : productList) {
//            products.add(productRepository.getAllByProductId(product.getId()));
//            for (Product product1 : products) {
//                productRepository.deleteById(product1.getId());
//            }
//        }
//    }
    
}

