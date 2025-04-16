package com.greenwaste.javadatabaseconnector.dtos.bucketwebdto;


import jakarta.validation.constraints.NotNull;

public class CreateBucketAssociationDTO {

    @NotNull(message = "Bucket ID is required")
    private Long bucketId;

    @NotNull(message = "Municipality ID is required")
    private Long municipalityId;

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public Long getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Long municipalityId) {
        this.municipalityId = municipalityId;
    }
}

