package com.threedgarden.api.service;


import com.threedgarden.api.dto.AddressRequest;
import com.threedgarden.api.dto.OrderRequest;
import com.threedgarden.api.model.Address;
import com.threedgarden.api.model.Category;
import com.threedgarden.api.model.Order;
import com.threedgarden.api.model.Users;
import com.threedgarden.api.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order deleteOrderById(Long id) {
        Order tmp = null;
        if (orderRepository.existsById(id)) {
            tmp = orderRepository.findById(id).get();
            orderRepository.deleteById(id);
            return tmp;
        }
        return tmp;
    }

    public Order updateOrderById(Long id, Order orderInfo) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()) throw new IllegalArgumentException("La categoría con id " + id + " no existe");

        Order order = optionalOrder.get();
        if(orderInfo.getDate() != null) order.setDate(orderInfo.getDate());
        if(orderInfo.getTotal() != null) order.setTotal(orderInfo.getTotal());
        if(orderInfo.getStatus()!= null) order.setStatus(orderInfo.getStatus());

        return orderRepository.save(order);
    }
//    @Transactional
//    public Users addUsersOrder(Long id, OrderRequest orderRequest) {
//        Users user = getUserById(id);
//
//        Address address = new Address();
//        address.setStreet(addressRequest.getStreet());
//        address.setExtNumber(addressRequest.getExtNumber());
//        address.setIntNumber(addressRequest.getIntNumber());
//        address.setNeighborhood(addressRequest.getNeighborhood());
//        address.setZipCode(addressRequest.getZipCode());
//        address.setCity(addressRequest.getCity());
//        address.setState(addressRequest.getState());
//        address.setUser(user);
//
//        addressRepository.save(address);
//        user.getAddresses().add(address);
//
//        return user;
//    }

//    @Transactional
//    public Order addOrderFromCart(CartRequest cartFromLocalStorage, Long userId) {
//        // 1. Crear una nueva orden
//        Order newOrder = new Order();
//        newOrder.setDate(LocalDate.now());
//        newOrder.setStatus("PENDING");
//        newOrder.setUser(userRepository.findById(userId).orElseThrow());
//
//        // 2. Calcular el total y crear los OrderDetail
//        double total = 0.0;
//        List<OrderDetail> details = new ArrayList<>();
//
//        for (CartItemDTO item : cartFromLocalStorage.getItems()) {
//            Products product = productRepository.findById(item.getProductId()).orElseThrow();
//
//            OrderDetail detail = new OrderDetail();
//            detail.setProduct(product);
//            detail.setQuantity(item.getQuantity());
//            detail.setUnitPrice(product.getPrice()); // Guardamos el precio actual
//            detail.setOrder(newOrder);
//
//            details.add(detail);
//            total += detail.getUnitPrice() * detail.getQuantity();
//        }
//
//        newOrder.setTotal(total);
//        newOrder.setOrderDetails(details);
//
//        // 3. Guardar la orden
//        return orderRepository.save(newOrder);
//    }
}