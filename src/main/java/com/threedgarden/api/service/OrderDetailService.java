package com.threedgarden.api.service;

import com.threedgarden.api.dto.OrderDetailRequest;
import com.threedgarden.api.model.OrderDetail;
import com.threedgarden.api.model.OrderEntity;
import com.threedgarden.api.model.Products;
import com.threedgarden.api.repository.OrderDetailRepository;
import com.threedgarden.api.repository.OrderRepository;
import com.threedgarden.api.repository.ProductsRepository;
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
    @Autowired
    private final ProductsRepository productsRepository;

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetail> getOrderDetailById(Long id) {
        return orderDetailRepository.findById(id);
    }
    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findByOrder_Id(orderId);
    }
    public OrderDetail updateOrderDetail(Long id, OrderDetailRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found with id: " + id));

        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setUnitPrice(request.getUnitPrice());

        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetail addOrderDetail(Long orderId, Long productId, OrderDetailRequest request) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setUnitPrice(request.getUnitPrice());

        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }


}