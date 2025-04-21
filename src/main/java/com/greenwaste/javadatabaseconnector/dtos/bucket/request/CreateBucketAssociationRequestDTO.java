package com.greenwaste.javadatabaseconnector.dtos.bucket.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBucketAssociationRequestDTO {
    private Long bucketId;
    private Long municipalityId;
}
