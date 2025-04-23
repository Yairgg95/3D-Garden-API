package com.threedgarden.api.service;


import com.threedgarden.api.dto.CharacteristicsRequest;
import com.threedgarden.api.model.Category;
import com.threedgarden.api.model.Characteristics;
import com.threedgarden.api.model.ProductCategoryLink;
import com.threedgarden.api.model.Products;
import com.threedgarden.api.repository.CategoryRepository;
import com.threedgarden.api.repository.CharacteristicsRepository;
import com.threedgarden.api.repository.ProductCategoryLinkRepository;
import com.threedgarden.api.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CharacteristicsRepository characteristicsRepository;
    private final ProductCategoryLinkRepository productCategoryLinkRepository;

    public ProductsService(ProductsRepository productsRepository, CharacteristicsRepository characteristicsRepository, ProductCategoryLinkRepository productCategoryLinkRepository) {
        this.productsRepository = productsRepository;
        this.characteristicsRepository = characteristicsRepository;
        this.productCategoryLinkRepository = productCategoryLinkRepository;
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

    public Products addCategoryToProduct(Long productId, Long categoryId) {
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        ProductCategoryLink link = new ProductCategoryLink();
        link.setProduct(product);
        link.setCategory(category);

        productCategoryLinkRepository.save(link);

        return product; // Devuelve el producto ya asociado, si quieres puedes incluir también las categorías
    }

    public void addCategoriesToProduct(Long productId, List<Long> categoryIds) {
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        for (Long categoryId : categoryIds) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Categoría con id " + categoryId + " no existe"));

            ProductCategoryLink link = new ProductCategoryLink();
            link.setProduct(product);
            link.setCategory(category);

            productCategoryLinkRepository.save(link);
        }
    }
}
