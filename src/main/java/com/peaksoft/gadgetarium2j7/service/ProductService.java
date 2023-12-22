package com.peaksoft.gadgetarium2j7.service;
import com.peaksoft.gadgetarium2j7.entity.Brand;
import com.peaksoft.gadgetarium2j7.entity.Product;
import com.peaksoft.gadgetarium2j7.entity.dto.ProductRequest;
import com.peaksoft.gadgetarium2j7.entity.dto.ProductResponse;
import com.peaksoft.gadgetarium2j7.mapper.ProductMapper;
import com.peaksoft.gadgetarium2j7.repository.BrandRepository;
import com.peaksoft.gadgetarium2j7.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
//    public AppResponse save(AppRequest request) {
//        Application application = applicationMapper.mapToEntity(request);
//        Genre genre = genreRepository.findById(request.getGenreId()).get();
//        application.setGenreName(genre.getName());
//        application.setGenre(genre);
//        repository.save(application);
//        return applicationMapper.mapToAppResponse(application);
//
//        public ProductResponse create(ProductRequest productRequest) {
//            Product product =productMapper.mapToEntity(productRequest);
//            Brand brand = brandRepository.findById(productRequest.getBrandId()).get();
//            product.setBrandName(brand.getName());
//            product.setBrand(brand);
//            productRepository.save(product);
//            return productMapper.mapToResponse(product);
//        }

    public ProductResponse create(ProductRequest productRequest) {
        Product product = productMapper.mapToEntity(productRequest);
        Brand brand = brandRepository.findByName(productRequest.getBrandName()).orElseThrow(()->new EntityNotFoundException("Brand not found"));
        if (brand == null) {
        } else {
            product.setBrandName(brand.getName());
            product.setBrand(brand);
            productRepository.save(product);
        }
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
            productRepository.deleteById(productId);
        }
        public ProductResponse getProductById(Long productId){
            Product product = productRepository.findById(productId).get();
            return  productMapper.mapToResponse(product);
        }
    public ProductResponse updateProduct(Long id, ProductRequest productRequest){
            Product product = productRepository.findById(id).get();
            product.setName(productRequest.getName());
            product.setCategory(productRequest.getCategory());
            product.setSubCategory(productRequest.getSubCategory());
            product.setGuarantee(productRequest.getGuarantee());
            product.setColor(productRequest.getColor());
            product.setMemory(productRequest.getMemory());
            product.setArticle(productRequest.getArticle());
            product.setScreen(productRequest.getScreen());
            product.setOperatingSystem(productRequest.getOperatingSystem());
            product.setSimCard(productRequest.getSimCard());
            product.setProcessor(productRequest.getProcessor());
            product.setWeight(productRequest.getWeight());
            product.setInStock(productRequest.getInStock());
            product.setWirelessInterface(productRequest.getWirelessInterface());
            product.setWaterResistance(productRequest.getWaterResistance());
            product.setReleaseDate(LocalDate.now());
            product.setImage(productRequest.getImage());
            product.setPrice(productRequest.getPrice());
            product.setCaseShape(productRequest.getCaseShape());
            product.setOperatingMemory(productRequest.getOperatingMemory());
            product.setVideo(product.getVideo());
            productRepository.save(product);
            return productMapper.mapToResponse(product);
    }
}
