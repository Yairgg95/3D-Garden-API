package com.threedgarden.api.model;


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
    @Column(name="type_movement", nullable = false)
    private String typeMovement;
    @Column(nullable = false)
    private int quantity;
    @Column(name="registration_date", nullable = false)
    private LocalDate registrationDate;

    //@ManyToOne
    //@JoinColumn(name="product_id", nullable = false)
    //private Products product;


}//cierre
