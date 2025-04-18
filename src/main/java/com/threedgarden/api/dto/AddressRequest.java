package com.threedgarden.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    private String street;
    private String extNumber;
    private String intNumber;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;
}
