package com.threedgarden.api.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
    public class OrderRequest {
    private Long id;
    private LocalDate date;
    private Double total;
    private String status;
    }

