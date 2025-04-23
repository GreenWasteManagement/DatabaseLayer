package com.greenwaste.javadatabaseconnector.dtos.bucket.request;

import lombok.*;

@Getter
@Setter
@Data
public class CreateBucketAssociationRequestDTO {
    private Long bucketId;
    private Long municipalityId;
}
