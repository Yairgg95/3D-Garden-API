package com.threedgarden.api.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "purchase_orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private LocalDate orderDate;
    @Column(nullable = false)
    private Double total;
    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;



}
