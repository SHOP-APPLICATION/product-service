package com.inventory.product.service.controllers;

import com.inventory.product.service.dto.SupplierRequestDto;
import com.inventory.product.service.dto.SupplierResponseDto;
import com.inventory.product.service.models.Supplier;
import com.inventory.product.service.services.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/catalog/suppliers")
@Slf4j
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    /*
     @PostMapping()
    public ResponseEntity<SupplierResponseDto> save(@Valid @RequestBody SupplierRequestDto requestDTO){
        SupplierResponseDto supplier = supplierService.addSupplier(requestDTO);
        return new ResponseEntity<>( supplier, HttpStatus.CREATED);
    }
        @PutMapping("/{id}")
        public ResponseEntity<SupplierResponseDto> edit(@Valid @RequestBody SupplierRequestDto requestDTO, @PathVariable UUID id){
            SupplierResponseDto categoryResponseDTO = supplierService.editSupplier(id, requestDTO);

            if (categoryResponseDTO != null) {
                return new ResponseEntity<>(categoryResponseDTO, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> delete(@PathVariable UUID id) {
            boolean check =  supplierService.deleteSupplier(id);
            if(check){
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        @GetMapping("/{id}")
        public ResponseEntity<SupplierResponseDto> get(@PathVariable UUID id){
            SupplierResponseDto categoryResponseDTO = supplierService.getSupplier(id);

            if (categoryResponseDTO == null) {
                return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return  new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
        }

        @GetMapping("/check/{id}")
        public ResponseEntity<Boolean> checkExist(@PathVariable UUID id){
            boolean check = supplierService.checkExistence(id);

            return  new ResponseEntity<>(check, HttpStatus.OK);
        }

        @GetMapping()
        public List<SupplierResponseDto> all () {
            return supplierService.getSuppliers();
        }*/
        @GetMapping
        public ResponseEntity<List<SupplierResponseDto>> getAllSuppliers() {
            List<SupplierResponseDto> suppliers = supplierService.getSuppliers();

            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<SupplierResponseDto> getSupplierById(@PathVariable UUID id) {
            SupplierResponseDto supplier = supplierService.getSupplier(id);
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<SupplierResponseDto> addSupplier(@Valid @RequestBody SupplierRequestDto supplierDto) {
            SupplierResponseDto supplierResponseDto = supplierService.addSupplier(supplierDto);
            return new ResponseEntity<>(supplierResponseDto, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<SupplierResponseDto> updateSupplier(@Valid @PathVariable UUID id, @RequestBody SupplierRequestDto supplierDto) {

            return new ResponseEntity<>(supplierService.editSupplier(id, supplierDto), HttpStatus.OK);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSupplier(@PathVariable UUID id) {
            supplierService.deleteSupplier(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}