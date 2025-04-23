package com.threedgarden.api.service;

import com.threedgarden.api.model.Order;
import com.threedgarden.api.model.OrderDetail;
import com.threedgarden.api.repository.OrderDetailRepository;
import com.threedgarden.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;


    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository, OrderRepository orderRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
    }

    public OrderDetail saveOrderDetail(Long orderId, OrderDetail orderDetail) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        orderDetail.setOrder(order);
        // Asigna los nuevos campos desde el objeto orderDetail que viene del frontend
        orderDetail.setProductName(orderDetail.getProductName());
        orderDetail.setProductId(orderDetail.getProductId());
        orderDetail.setUnitPrice(orderDetail.getUnitPrice());
        orderDetail.setQuantity(orderDetail.getQuantity());
        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetail> getOrderDetailById(Integer id) {
        return orderDetailRepository.findById(id);
    }

    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(Integer id) {
        orderDetailRepository.deleteById(id);
    }

    public OrderDetail updateOrderDetail(Integer id, OrderDetail orderDetailDetails) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order detail not found with id: " + id));

        orderDetail.setUnitPrice(orderDetailDetails.getUnitPrice());
        orderDetail.setQuantity(orderDetailDetails.getQuantity());

        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findByOrder_OrderId(orderId);
    }
}