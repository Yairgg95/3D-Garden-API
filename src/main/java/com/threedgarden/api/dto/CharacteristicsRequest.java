package com.threedgarden.api.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacteristicsRequest {
    private Long id;
    private String material_type;
    private Double weight;
    private Double width;
    private Double height;
    private Double depth;
    private String color;
}
