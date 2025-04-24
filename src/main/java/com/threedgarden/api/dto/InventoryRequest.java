package com.threedgarden.api.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InventoryRequest {
    private String status;
    private int quantity;
    private LocalDate registrationDate;

}
