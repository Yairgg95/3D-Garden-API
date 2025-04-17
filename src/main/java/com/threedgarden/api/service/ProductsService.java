package com.threedgarden.api.service;

import com.threedgarden.api.model.Products;
import com.threedgarden.api.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Autowired
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }
    public Products getProductById(Long id){
        return productsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("El producto con id " + id + " no se encontró")
        );
    }

    public Products deleteProductById(Long id){
        Products tmp = null;
        if(productsRepository.existsById(id)){
            tmp = productsRepository.findById(id).get();
            productsRepository.deleteById(id);
            return tmp;
        }
        return tmp;
    }

    public Products addProduct(Products product){
        return productsRepository.save(product);
    }

    public Products updateProduct(Long id, Products productDetails){
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new IllegalArgumentException("El producto con el id "+ id + " no existe");
        }
        Products product = optionalProduct.get();
        if(productDetails.getName() != null){
            product.setName(productDetails.getName());
        }
        if(productDetails.getDescription() != null){
            product.setDescription(productDetails.getDescription());
        }
        if(productDetails.getPrice() != null){
            product.setPrice(productDetails.getPrice());
        }
        if(productDetails.getStock() != null){
            product.setStock(productDetails.getStock());
        }
        if(productDetails.getImage() != null){
            product.setImage(productDetails.getImage());
        }
        return productsRepository.save(product);
    }
}