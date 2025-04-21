package com.greenwaste.javadatabaseconnector.dtos.base;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Data
public class ContainerUnloadingDTO {

    private Long id;
    private Long containerId;
    private Long userId;
    private BigDecimal unloadedQuantity;
    private Instant unloadingTimestamp;

}

