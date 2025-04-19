package com.greenwaste.javadatabaseconnector.dtos.bucketwebdto.older;

import java.time.Instant;

public class GetBucketMunicipalityDTO {
    private Long associationId;
    private Long bucketId;
    private Long userId;
    private Instant timestampOfAssociation;
    private Boolean status;

    public GetBucketMunicipalityDTO() {}

    public GetBucketMunicipalityDTO(Long associationId, Long bucketId, Long userId,
                                    Instant timestampOfAssociation, Boolean status) {
        this.associationId = associationId;
        this.bucketId = bucketId;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
