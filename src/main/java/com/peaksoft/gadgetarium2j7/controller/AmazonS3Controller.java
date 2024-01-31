package com.peaksoft.gadgetarium2j7.controller;

import com.peaksoft.gadgetarium2j7.model.entities.Product;
import com.peaksoft.gadgetarium2j7.repository.ProductRepository;
import com.peaksoft.gadgetarium2j7.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class AmazonS3Controller {

    private final AmazonS3Service amazonS3Service;
    private final ProductRepository productRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(amazonS3Service.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = amazonS3Service.downloadFile(fileName);
        ByteArrayResource byteArrayResource = new ByteArrayResource(data);
        return ResponseEntity.ok().contentLength(data.length).
                header("Content-type", "application/octet-stream").
                header("Content-disposition", "attachment; filename=\"" + fileName + "\"").body(byteArrayResource);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(amazonS3Service.deleteFile(fileName), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<String> uploadPhotoToProduct(@RequestParam Long productId, @RequestParam(value = "file") MultipartFile file) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        Product product = productOptional.get();
        amazonS3Service.addPhotoToProduct(product, file);
        return new ResponseEntity<>("Photo uploaded to product with id: " + productId, HttpStatus.OK);
    }
    }

