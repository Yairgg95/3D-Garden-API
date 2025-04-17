package com.threedgarden.api.service;

import com.threedgarden.api.model.Inventory;
import com.threedgarden.api.repository.InventoryRepository;
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

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id){
        return inventoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El producto con el " + id + " no existe en el inventario")
        );
    }

    public Inventory addInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    public Inventory deleteInventoryById(Long id) {
        Inventory tmp = null;
        if(inventoryRepository.existsById(id)){
            tmp = inventoryRepository.findById(id).get();
            inventoryRepository.deleteById(id);
            return tmp;
        }
        return tmp;
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existingInventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        existingInventory.setStatus(inventory.getStatus());
        existingInventory.setQuantity(inventory.getQuantity());
        existingInventory.setRegistrationDate(inventory.getRegistrationDate());

        return inventoryRepository.save(existingInventory);

    }


}//cierre
