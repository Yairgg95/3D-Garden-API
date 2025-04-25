package com.threedgarden.api.service;


import com.threedgarden.api.dto.*;
import com.threedgarden.api.model.*;
import com.threedgarden.api.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CharacteristicsRepository characteristicsRepository;
    private final ProductCategoryLinkRepository productCategoryLinkRepository;
    private final InventoryRepository inventoryRepository;
    private final OrderDetailRepository orderDetailRepository;

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
        if(productDetails.getStock() != null){
            product.setStock(productDetails.getStock());
        }
        if(productDetails.getPrice() != null){
            product.setPrice(productDetails.getPrice());
        }
        if(productDetails.getImage() != null){
            product.setImage(productDetails.getImage());
        }
        return productsRepository.save(product);
    }

    @Transactional
    public Products addCharacteristics(Long id, CharacteristicsRequest characteristicsRequest){
        Products product = productsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("El producto con id" + id + " no se encontró")
        );
        Characteristics characteristics = new Characteristics();
        if(characteristicsRequest.getHeight() != null){
            characteristics.setHeight(characteristicsRequest.getHeight());
        }
        if(characteristicsRequest.getWidth() != null){
            characteristics.setWidth(characteristicsRequest.getWidth());
        }
        if(characteristicsRequest.getDepth() != null){
            characteristics.setDepth(characteristicsRequest.getDepth());
        }
        if(characteristicsRequest.getColor() != null){
            characteristics.setColor(characteristicsRequest.getColor());
        }
        if(characteristicsRequest.getWeight() != null){
            characteristics.setWeight(characteristicsRequest.getWeight());
        }
        if(characteristicsRequest.getMaterial_type() != null){
            characteristics.setMaterial_type(characteristicsRequest.getMaterial_type());
        }
        characteristics.setProduct(product);
        product.setCharacteristics(characteristics);
        characteristicsRepository.save(characteristics);
        return productsRepository.save(product);
    }

    public Products updateCharacteristics(Long id, CharacteristicsRequest characteristicsRequest) {
        Products product = productsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El producto con id " + id + " no se encontró")
        );
        Characteristics characteristics = product.getCharacteristics();
        if (characteristics == null) {
            throw new IllegalArgumentException("El producto con id " + id + " no tiene características asociadas");
        }
        if (characteristicsRequest.getHeight() != null) {
            characteristics.setHeight(characteristicsRequest.getHeight());
        }
        if (characteristicsRequest.getWidth() != null) {
            characteristics.setWidth(characteristicsRequest.getWidth());
        }
        if (characteristicsRequest.getDepth() != null) {
            characteristics.setDepth(characteristicsRequest.getDepth());
        }
        if (characteristicsRequest.getColor() != null) {
            characteristics.setColor(characteristicsRequest.getColor());
        }
        if (characteristicsRequest.getWeight() != null) {
            characteristics.setWeight(characteristicsRequest.getWeight());
        }
        if (characteristicsRequest.getMaterial_type() != null) {
            characteristics.setMaterial_type(characteristicsRequest.getMaterial_type());
        }
        characteristicsRepository.save(characteristics);
        return product;
    }

    public Products addCategoryToProduct(Long id, CategoryRequest categoryRequest){
        Products newProduct = getProductById(id);

        ProductCategoryLink newCategoryLink = new ProductCategoryLink();
        Category newCategory = new Category();

        newCategory.setName(categoryRequest.getName());
        newCategory.setDescription(categoryRequest.getDescription());

        newCategoryLink.setCategory(newCategory);
        newCategoryLink.setProduct(newProduct);

        productCategoryLinkRepository.save(newCategoryLink);

        return newProduct;
    }

    @Transactional
    public Products addIventoryToProduct(Long id, InventoryRequest inventoryRequest){
        Products newProduct = productsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El producto con id " + id + " no se encontró")
        );

        Inventory newInventory = new Inventory();
        newInventory.setRegistrationDate(inventoryRequest.getRegistrationDate());
        newInventory.setQuantity(inventoryRequest.getQuantity());
        newInventory.setStatus(inventoryRequest.getStatus());

        newInventory.setProduct(newProduct);
        inventoryRepository.save(newInventory);

        newProduct.getInventories().add(newInventory);
        productsRepository.save(newProduct);

        return newProduct;
    }

    @Transactional
    public Products addProductToOrderDetails(Long id, OrderDetailRequest orderDetailRequest){
        Products newProduct = getProductById(id);

        OrderDetail newOrderDetail = new OrderDetail();
        newOrderDetail.setQuantity(orderDetailRequest.getQuantity());
        newOrderDetail.setUnitPrice(orderDetailRequest.getUnitPrice());
        newOrderDetail.setProduct(newProduct);

        orderDetailRepository.save(newOrderDetail);
        newProduct.getOrderDetails().add(newOrderDetail);

        return newProduct;
    }


}
