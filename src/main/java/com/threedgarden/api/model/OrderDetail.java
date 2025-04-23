package com.threedgarden.api.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailId;

    private String productName;
    private Integer productId;
    private Integer unitPrice;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore // Mantén esto para evitar la serialización recursiva de la Order padre
    private Order order;


    public Long getOrderId() {
        return this.order != null ? this.order.getOrderId() : null;
    }

    public OrderDetail() {
    }

    public OrderDetail(String productName, Integer productId, Integer unitPrice, Integer quantity, Order order) {
        this.productName = productName;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.order = order;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
