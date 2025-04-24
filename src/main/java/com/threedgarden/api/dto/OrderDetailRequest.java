package com.threedgarden.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailRequest {
    private Integer quantity;
    private List<Long> productIds;
    private List<Long> orderIds;
}
