package com.threedgarden.api.service;

import com.threedgarden.api.model.OrderEntity;
import com.threedgarden.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<OrderEntity> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderEntity addOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    public OrderEntity deleteOrderById(Long id) {
        OrderEntity tmp = null;
        if (orderRepository.existsById(id)) {
            tmp = orderRepository.findById(id).get();
            orderRepository.deleteById(id);
            return tmp;
        }
        return tmp;
    }

    public OrderEntity updateOrderById(Long id, OrderEntity orderEntityInfo) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()) throw new IllegalArgumentException("La categoría con id " + id + " no existe");

        OrderEntity orderEntity = optionalOrder.get();
        if(orderEntityInfo.getOrderDate() != null) orderEntity.setOrderDate(orderEntityInfo.getOrderDate());
        if(orderEntityInfo.getTotal() != null) orderEntity.setTotal(orderEntityInfo.getTotal());
        if(orderEntityInfo.getStatus()!= null) orderEntity.setStatus(orderEntityInfo.getStatus());

        return orderRepository.save(orderEntity);
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