package com.threedgarden.api.service;

import com.threedgarden.api.model.Order;
import com.threedgarden.api.model.OrderDetail;
import com.threedgarden.api.repository.OrderDetailRepository;
import com.threedgarden.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    @Autowired
    private final OrderDetailRepository orderDetailRepository;
    @Autowired
    private final OrderRepository orderRepository;

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetail> getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id);
    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }

    public OrderDetail updateOrderDetail(Long id, OrderDetail newOrderDetail) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order detail not found with id: " + id));

        orderDetail.setUnitPrice(newOrderDetail.getUnitPrice());
        orderDetail.setQuantity(newOrderDetail.getQuantity());

        return orderDetailRepository.save(orderDetail)
                ;
    }

}