package com.threedgarden.api.model;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Integer quantity;

    @Column
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

}
