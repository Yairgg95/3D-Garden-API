package com.threedgarden.api.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductCategoryAssociationRequest {
    private List<Long> categoryIds;
}