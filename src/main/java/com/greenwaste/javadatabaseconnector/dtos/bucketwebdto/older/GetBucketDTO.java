package com.greenwaste.javadatabaseconnector.dtos.bucketwebdto.older;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class GetBucketDTO {
    // Getters e Setters
    private Long id;
    private BigDecimal capacity;
    private Boolean isAssociated;

    public GetBucketDTO(Long id, BigDecimal capacity, Boolean isAssociated) {
        this.id = id;
        this.capacity = capacity;
        this.isAssociated = isAssociated;
    }

}
