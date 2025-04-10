package com.threedgarden.api.controller;

import com.threedgarden.api.model.Products;
import com.threedgarden.api.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products/")
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

    @GetMapping(path="{productId}")
    public Products getProductById(@PathVariable("productId") Long id){
        return productsService.getProductById(id);
    }

    @PostMapping
    public Products addProduct(@RequestBody Products product){
        return productsService.addProduct(product);
    }

    @DeleteMapping(path="{productId}")
    public Products deleteProductById(@PathVariable("productId")Long id){
        return productsService.deleteProductById(id);
    }

    @PutMapping(path="{productId}")
    public Products updateProductById(@PathVariable("productId")Long id, @RequestBody Products product){
        return productsService.updateProduct(id,product);
    }
}
