package com.threedgarden.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private Long stock;
    @Lob
    @Column(nullable = false)
    private Byte image;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "characteristics_id", referencedColumnName = "id")
    private Characteristics characteristics;

   @OneToMany(mappedBy = "product")
   @JsonIgnore //para evitar recursion infinita si devuelves el producto
   private List<Inventory> inventories;

}
