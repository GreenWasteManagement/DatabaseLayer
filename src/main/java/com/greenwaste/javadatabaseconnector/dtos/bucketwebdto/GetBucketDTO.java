package com.greenwaste.javadatabaseconnector.dtos.bucketwebdto;

import java.math.BigDecimal;

public class GetBucketDTO {
    private Long id;
    private BigDecimal capacity;
    private Boolean isAssociated;

    public GetBucketDTO(Long id, BigDecimal capacity, Boolean isAssociated) {
        this.id = id;
        this.capacity = capacity;
        this.isAssociated = isAssociated;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public Boolean getIsAssociated() {
        return isAssociated;
    }

    public void setIsAssociated(Boolean isAssociated) {
        this.isAssociated = isAssociated;
    }
}
