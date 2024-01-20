package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.dto.BrandRequest;
import com.peaksoft.gadgetarium2j7.model.dto.BrandResponse;
import com.peaksoft.gadgetarium2j7.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/create")
    public ResponseEntity<BrandResponse> add(@RequestBody BrandRequest brandRequest){
        BrandResponse brandResponse = brandService.creat(brandRequest);
        return new ResponseEntity<>(brandResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        brandService.delete(id);
        return "Delete brand with id "+id+" successfully delete";
    }

    @GetMapping()
    public List<BrandResponse> getAllBrand(){
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public BrandResponse findById(@PathVariable Long id){
        return brandService.getBrandById(id);
    }

    @PatchMapping("/update/{id}")
    public BrandResponse brandResponse(@PathVariable("id")Long id, @RequestBody BrandRequest brandRequest){
        return brandService.update(id, brandRequest);
    }


}
