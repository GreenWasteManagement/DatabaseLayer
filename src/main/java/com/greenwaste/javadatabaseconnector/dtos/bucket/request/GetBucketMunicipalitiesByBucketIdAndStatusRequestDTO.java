package com.greenwaste.javadatabaseconnector.dtos.bucket.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class GetBucketMunicipalitiesByBucketIdAndStatusRequestDTO {
    private Long bucketId;
    private Boolean status;
}
