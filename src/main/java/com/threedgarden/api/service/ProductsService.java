package com.threedgarden.api.service;


import com.threedgarden.api.dto.CategoryRequest;
import com.threedgarden.api.dto.CharacteristicsRequest;
import com.threedgarden.api.dto.InventoryRequest;
import com.threedgarden.api.dto.OrderRequest;
import com.threedgarden.api.model.*;
import com.threedgarden.api.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CharacteristicsRepository characteristicsRepository;
    private final ProductCategoryLinkRepository productCategoryLinkRepository;

    public ProductsService(ProductsRepository productsRepository, CharacteristicsRepository characteristicsRepository, ProductCategoryLinkRepository productCategoryLinkRepository,
                           InventoryRepository inventoryRepository) {
        this.productsRepository = productsRepository;
        this.characteristicsRepository = characteristicsRepository;
        this.productCategoryLinkRepository = productCategoryLinkRepository;
        this.inventoryRepository = inventoryRepository;
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
        if(productDetails.getImage() != null){
            product.setImage(productDetails.getImage());
        }
        return productsRepository.save(product);
    }

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

    @Autowired
    private CategoryRepository categoryRepository;
    private final InventoryRepository inventoryRepository;

    public Products addCategorytoProduct(Long productId, CategoryRequest categoryRequest){
        Products product = productsRepository.findById(productId).orElseThrow(
                ()-> new IllegalArgumentException("El producto con id" + productId + " no se encontró")
        );
        Category category = categoryRepository.findByName(categoryRequest.getName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(categoryRequest.getName());
                    newCategory.setDescription(categoryRequest.getDescription());
                    return categoryRepository.save(newCategory);
                });

        // Verificar que no exista ya ese vínculo
        boolean alreadyLinked = product.getProductCategories().stream()
                .anyMatch(link -> link.getCategory().getId().equals(category.getId()));

        if (!alreadyLinked) {
            ProductCategoryLink link = new ProductCategoryLink();
            link.setProduct(product);
            link.setCategory(category);

            product.getProductCategories().add(link);
            category.getProductCategories().add(link);

            // Persistir la relación
            productCategoryLinkRepository.save(link);
        }

        return productsRepository.save(product); // opcional, si necesitas el return actualizado
    }

    public Products addInvetory(Long id, InventoryRequest inventoryRequest){
        Products product = productsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("El producto con id" + id + " no se encontró")
        );
        Inventory newInventory = new Inventory();
        if(inventoryRequest.getQuantity() != 0){
            newInventory.setQuantity(inventoryRequest.getQuantity());
        }
        if(inventoryRequest.getStatus() != null){
            newInventory.setStatus(inventoryRequest.getStatus());
        }
        if(inventoryRequest.getRegistrationDate() != null){
            newInventory.setRegistrationDate(LocalDate.now());
        }

        newInventory.setProduct(product);
        product.getInventories().add(newInventory);
        inventoryRepository.save(newInventory);
        return productsRepository.save(product);
    }


}
