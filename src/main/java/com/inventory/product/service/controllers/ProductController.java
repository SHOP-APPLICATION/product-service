package com.inventory.product.service.controllers;


import com.inventory.product.service.dto.ProductRequestDto;
import com.inventory.product.service.dto.ProductResponseDto;
import com.inventory.product.service.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/catalog/products")
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductRequestDto requestDTO){

        return new ResponseEntity<>(productService.addProduct(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> edit(@Valid @RequestBody ProductRequestDto requestDTO, @PathVariable UUID id){
        ProductResponseDto productResponseDto = productService.editProduct(id, requestDTO);

        if (productResponseDto != null) {
            return new ResponseEntity<>(productResponseDto, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        boolean check =  productService.deleteProduct(id);
        if(check){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> get(@PathVariable UUID id){
        ProductResponseDto productResponseDto = productService.getProduct(id);

        if (productResponseDto== null) {
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<Boolean> checkExist(@PathVariable UUID id){
        boolean check = productService.checkExistence(id);

        return  new ResponseEntity<>(check, HttpStatus.OK);
    }

    @GetMapping()
    public List<ProductResponseDto> all() {
        return productService.getProducts();
    }
}
