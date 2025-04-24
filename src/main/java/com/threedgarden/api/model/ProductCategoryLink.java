package com.threedgarden.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_has_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCategoryLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product-categories")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
