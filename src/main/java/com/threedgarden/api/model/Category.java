package com.threedgarden.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<ProductCategoryLink> productCategories;
}
