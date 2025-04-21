package com.greenwaste.javadatabaseconnector.dtos.bucket.response;

import com.greenwaste.javadatabaseconnector.dtos.base.BucketMunicipalityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBucketMunicipalityByIdResponseDTO {
    private BucketMunicipalityDTO bucketMunicipality;
}
