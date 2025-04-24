package com.threedgarden.api.controller;

import com.threedgarden.api.model.Order;
import com.threedgarden.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody Order orderInfo) {
        try {
            Order updatedOrder = orderService.updateOrderById(id, orderInfo);
            return ResponseEntity.ok(updatedOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<Order> checkout(@RequestBody CartRequest cartRequest, Authentication authentication) {
//        // Aquí obtenemos directamente el username del token de autenticación
//        String username = authentication.g;
//
//        // Pasamos el username al servicio en lugar del userId
//        Order order = orderService.createOrderFromCart(cartRequest, username);
//
//        return ResponseEntity.ok(order);
//    }


}
