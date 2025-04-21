package com.greenwaste.javadatabaseconnector.dtos.base;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Data
public class BucketMunicipalityContainerDTO {

    private Long id;
    private Long associationId;
    private Long containerId;
    private Instant depositTimestamp;
    private BigDecimal depositAmount;

}

