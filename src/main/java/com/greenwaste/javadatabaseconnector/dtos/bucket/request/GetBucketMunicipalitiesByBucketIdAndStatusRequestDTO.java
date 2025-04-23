package com.greenwaste.javadatabaseconnector.dtos.bucket.request;

import lombok.*;

@Getter
@Setter
@Data
public class GetBucketMunicipalitiesByBucketIdAndStatusRequestDTO {
    private Long bucketId;
    private Boolean status;
}
