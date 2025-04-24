package com.threedgarden.api.service;

import com.threedgarden.api.model.Inventory;
import com.threedgarden.api.model.Products;
import com.threedgarden.api.repository.InventoryRepository;
import com.threedgarden.api.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }
    @Autowired
    private ProductsRepository productsRepository;

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id){
        return inventoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El producto con el id " + id + " no existe en el inventario")
        );
    }

    public Inventory addInventory(Inventory inventory){
        Products product = inventory.getProduct();
        Optional<Products> optionalProduct = productsRepository.findById(product.getId());
        return inventoryRepository.save(inventory);
    }

    public Inventory deleteInventoryById(Long id) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);

        if (optionalInventory.isEmpty()) {
            throw new RuntimeException("Inventario no encontrado con ID: " + id);
        }

        Inventory inventory = optionalInventory.get();
        Products product = inventory.getProduct();

        if (product == null || product.getId() == null) {
            throw new RuntimeException("El inventario no tiene un producto asociado válido.");
        }

        Optional<Products> optionalProduct = productsRepository.findById(product.getId());
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Producto no encontrado con ID: " + product.getId());
        }

        Products existingProduct = optionalProduct.get();

        productsRepository.save(existingProduct);
        inventoryRepository.deleteById(id);

        return inventory;
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        Products product = existingInventory.getProduct();
        if (product == null || product.getId() == null){
            throw new RuntimeException("El inventario no tiene un producto asociado válido.");
        }

        Products existingProduct = productsRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Producto asociado no encontrado"));

        String oldStatus = existingInventory.getStatus();
        Integer oldQuantity = existingInventory.getQuantity();

            String newStatus = inventory.getStatus();
            Integer newQuantity = inventory.getQuantity();

            if (newStatus == null || newQuantity == null){
                throw new RuntimeException("Debe especificar un estatus y una cantidad.");
            }

            existingInventory.setStatus(newStatus);
            existingInventory.setQuantity(newQuantity);
            existingInventory.setRegistrationDate(inventory.getRegistrationDate());

            productsRepository.save(existingProduct);
            return inventoryRepository.save(existingInventory);
    }


}//cierre
