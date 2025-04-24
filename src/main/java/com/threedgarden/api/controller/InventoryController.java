package com.threedgarden.api.controller;

import com.threedgarden.api.model.Inventory;
import com.threedgarden.api.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/inventories") //http://localhost:8080/api/inventories
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping(path = "/{id}")
    public Inventory getInventoryById(@PathVariable("id") Long id) {
        return inventoryService.getInventoryById(id);
    }

    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteInventoryById(@PathVariable("id")Long id){
        try {
            Inventory deletedInventory = inventoryService.deleteInventoryById(id);
            return ResponseEntity.ok(deletedInventory);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        return inventoryService.updateInventory(id, inventory);
    }


}//cierre


