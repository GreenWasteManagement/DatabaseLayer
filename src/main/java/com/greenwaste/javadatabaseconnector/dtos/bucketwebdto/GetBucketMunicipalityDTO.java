package com.greenwaste.javadatabaseconnector.dtos.bucketwebdto;

import java.time.Instant;

public class GetBucketMunicipalityDTO {
    private Long associationId;
    private Long bucketId;
    private Long municipalityId;
    private Instant timestampOfAssociation;
    private Boolean status;

    public GetBucketMunicipalityDTO() {}

    public GetBucketMunicipalityDTO(Long associationId, Long bucketId, Long municipalityId,
                                    Instant timestampOfAssociation, Boolean status) {
        this.associationId = associationId;
        this.bucketId = bucketId;
        this.municipalityId = municipalityId;
        this.timestampOfAssociation = timestampOfAssociation;
        this.status = status;
    }

    // Getters and Setters
    public Long getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Long associationId) {
        this.associationId = associationId;
    }

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

    public Instant getTimestampOfAssociation() {
        return timestampOfAssociation;
    }

    public void setTimestampOfAssociation(Instant timestampOfAssociation) {
        this.timestampOfAssociation = timestampOfAssociation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
