package com.threedgarden.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailRequest {
    private Integer quantity;
    private Double unitPrice;
}
