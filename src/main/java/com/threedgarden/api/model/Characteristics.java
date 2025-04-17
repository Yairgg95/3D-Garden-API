package com.threedgarden.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "characteristics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(nullable = false)
    private String material_type;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private Double width;
    @Column(nullable = false)
    private Double height;
    @Column(nullable = false)
    private Double depth;
    @Column(nullable = false)
    private String color;

    @OneToOne(mappedBy = "characteristics")
    @JsonIgnore
    private Products product;
}
