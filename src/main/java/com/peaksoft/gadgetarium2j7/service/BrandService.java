package com.peaksoft.gadgetarium2j7.service;

import com.peaksoft.gadgetarium2j7.mapper.BrandMapper;
import com.peaksoft.gadgetarium2j7.model.dto.BrandRequest;
import com.peaksoft.gadgetarium2j7.model.dto.BrandResponse;
import com.peaksoft.gadgetarium2j7.model.entities.Brand;
import com.peaksoft.gadgetarium2j7.repository.BrandRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandMapper brandMapper;
    private final BrandRepository brandRepository;

    public BrandResponse creat(BrandRequest brandRequest) {
        Brand brand = brandMapper.mapToEntity(brandRequest);
        brandRepository.save(brand);
        return brandMapper.mapToResponse(brand);
    }

    public List<BrandResponse> getAll() {
        List<BrandResponse> brandResponses = new ArrayList<>();
        for (Brand brand : brandRepository.findAll()) {
            brandResponses.add(brandMapper.mapToResponse(brand));
        }
        return brandResponses;
    }


    public void delete(Long brandById) {
        brandRepository.findById(brandById)
                .orElseThrow(() -> new EntityNotFoundException("Brand with id " + brandById + " not found"));
        brandRepository.deleteById(brandById);
    }

    public BrandResponse getBrandById(Long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new EntityNotFoundException("Brand with id " + brandId + " not found"));
        return brandMapper.mapToResponse(brand);
    }

    public BrandResponse update(Long id, BrandRequest brandRequest) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Brand with id " + id + " not found"));
        brand.setName(brandRequest.getName());
        brand.setImage(brandRequest.getImage());
        brandRepository.save(brand);
        return brandMapper.mapToResponse(brand);
    }

    public boolean existsById(Long id) {
        return brandRepository.existsById(id);
    }


}
