package com.threedgarden.api.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="inventory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name="status", nullable = false)
    private String status;
    @Column(nullable = false)
    private int quantity;
    @Column(name="registration_date", nullable = false)
    private LocalDate registrationDate;


    @ManyToOne(fetch = FetchType.EAGER) //Para que cargue el producto completo
    @JoinColumn(name="product_id")
    @JsonBackReference(value = "product-inventories")
    private Products product;


}//cierre
