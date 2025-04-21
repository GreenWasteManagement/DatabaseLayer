package com.greenwaste.javadatabaseconnector.dtos.base;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Data
public class BucketMunicipalityDTO {

    private Long id;
    private Long userId;
    private Long bucketId;
    private Instant timestampOfAssociation;
    private Boolean status;

}

