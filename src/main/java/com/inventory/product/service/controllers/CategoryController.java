package com.inventory.product.service.controllers;


import com.inventory.product.service.dto.CategoryRequestDto;
import com.inventory.product.service.dto.CategoryResponseDTO;
import com.inventory.product.service.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/catalog/categories")
@Slf4j
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('CATEGORIES')")
    public ResponseEntity<CategoryResponseDTO> save(@Valid @RequestBody CategoryRequestDto requestDTO, Principal principal){

        // access to the user autehnicated
        KeycloakAuthenticationToken keycloakAuthenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken token = keycloakAuthenticationToken.getAccount().getKeycloakSecurityContext().getToken();

        String user = token.getPreferredUsername();

        /* return ResponseEntity.ok(addedCategory);*/
        return new ResponseEntity<>(categoryService.addCategory(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> edit(@Valid @RequestBody CategoryRequestDto requestDTO, @PathVariable UUID id){
        CategoryResponseDTO categoryResponseDTO = categoryService.editCategory(id, requestDTO);

        if (categoryResponseDTO != null) {
            return new ResponseEntity<>(categoryResponseDTO, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        boolean check =  categoryService.deleteCategory(id);
        if(check){
            return ResponseEntity.noContent().build();
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> get(@PathVariable UUID id){
        CategoryResponseDTO categoryResponseDTO = categoryService.getCategory(id);
        if (categoryResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity<>(categoryResponseDTO, HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('CATEGORIES')")
    public ResponseEntity<List<CategoryResponseDTO>> getAll () {
       return  ResponseEntity.ok(categoryService.getCategories());
    }
}
