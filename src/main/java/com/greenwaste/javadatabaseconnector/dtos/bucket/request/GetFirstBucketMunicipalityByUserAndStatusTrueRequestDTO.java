package com.greenwaste.javadatabaseconnector.dtos.bucket.request;

import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetFirstBucketMunicipalityByUserAndStatusTrueRequestDTO {
    private MunicipalityDTO municipality;
}
