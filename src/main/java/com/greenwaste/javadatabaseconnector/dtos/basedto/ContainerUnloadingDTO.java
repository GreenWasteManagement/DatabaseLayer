package com.greenwaste.javadatabaseconnector.dtos.basedto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class ContainerUnloadingDTO {

    private Long id;
    private Long containerId;
    private Long userId;
    private BigDecimal unloadedQuantity;
    private Instant unloadingTimestamp;

}

