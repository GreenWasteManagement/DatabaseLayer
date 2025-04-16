package com.greenwaste.javadatabaseconnector.dtos.bucketwebdto;


import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateBucketRequestDTO {

    @NotNull(message = "Bucket ID is required")
    private Long id;

    private BigDecimal capacity;
    private Boolean isAssociated;

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

