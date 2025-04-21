package com.greenwaste.javadatabaseconnector.dtos.bucket.response;

import com.greenwaste.javadatabaseconnector.dtos.base.BucketDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBucketAssociationResponseDTO {
    private Long id;
    private BucketDTO bucket;
    private MunicipalityDTO municipality;
    private Instant timestampOfAssociation;
    private Boolean status;
}
