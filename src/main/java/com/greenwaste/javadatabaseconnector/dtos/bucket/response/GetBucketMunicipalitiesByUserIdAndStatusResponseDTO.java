package com.greenwaste.javadatabaseconnector.dtos.bucket.response;

import com.greenwaste.javadatabaseconnector.dtos.base.BucketMunicipalityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBucketMunicipalitiesByUserIdAndStatusResponseDTO {
    private List<BucketMunicipalityDTO> bucketMunicipalities;
}
