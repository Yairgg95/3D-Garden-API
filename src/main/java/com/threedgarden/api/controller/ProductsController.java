package com.threedgarden.api.controller;


import com.threedgarden.api.dto.CategoryRequest;
import com.threedgarden.api.dto.CharacteristicsRequest;
import com.threedgarden.api.dto.ProductCategoryAssociationRequest;
import com.threedgarden.api.model.Products;
import com.threedgarden.api.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    @GetMapping
    public List<Products> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping(path="/{productId}")
    public Products getProductById(@PathVariable("productId") Long id){
        return productsService.getProductById(id);
    }

    @PostMapping
    public Products addProduct(@RequestBody Products product){
        return productsService.addProduct(product);
    }

    @DeleteMapping(path="/{productId}")
    public Products deleteProductById(@PathVariable("productId")Long id){
        return productsService.deleteProductById(id);
    }

    @PutMapping(path="/{productId}")
    public Products updateProductById(@PathVariable("productId")Long id, @RequestBody Products product){
        return productsService.updateProduct(id,product);
    }

    // add characteristic to a product
    @PostMapping(path="/{productId}/add-characteristics")
    public Products addCharacteristicsProduct(@PathVariable("productId") long id, @RequestBody CharacteristicsRequest characteristicsRequest){
        return productsService.addCharacteristics(id, characteristicsRequest);
    }

    // update characteristic from a product
    @PutMapping(path="/{productId}/update-characteristics")
    public Products updateCharacteristicsProduct(@PathVariable("productId") long id, @RequestBody CharacteristicsRequest characteristicsRequest){
        return productsService.updateCharacteristics(id, characteristicsRequest);
    }

    @PostMapping("/{productId}/add-category")
    public ResponseEntity<Products> addCategoryToProduct(
            @PathVariable Long productId,
            @RequestBody CategoryRequest categoryRequest) {
        Products updatedProduct = productsService.addCategorytoProduct(productId, categoryRequest);
        return ResponseEntity.ok(updatedProduct);
    }
}

